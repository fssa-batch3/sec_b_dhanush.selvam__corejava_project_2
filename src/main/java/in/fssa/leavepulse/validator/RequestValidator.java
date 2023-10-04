package in.fssa.leavepulse.validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.leavepulse.dao.RequestDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Request;
import in.fssa.leavepulse.service.RequestService;
import in.fssa.leavepulse.util.StringUtil;

public class RequestValidator {

	/**
	 * 
	 * @param request
	 * @throws ValidationException
	 */
	public static void validateRequest(Request request) throws ValidationException {

		if (request == null)
			throw new ValidationException("Request cannot be null");
	}

	/**
	 * 
	 * @param requestId
	 * @throws ValidationException
	 */
	public static void validateRequestId(int requestId) throws ValidationException {

		StringUtil.rejectIfInvalidId(requestId, "Request Id");

	}

	/**
	 * 
	 * @param requestId
	 * @throws ValidationException
	 */
	public static void checkRequestIdExist(int requestId) throws ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			if (requestDAO.findRequestByRequestId(requestId) == null)
				throw new ValidationException("Request Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param startDate
	 * @throws ValidationException
	 */
	public static void validateStartDate(LocalDate startDate) throws ValidationException {

		if (startDate == null)
			throw new ValidationException("Start Date should not be empty");

		if (startDate.isBefore(LocalDate.now()))
			throw new ValidationException("Start Date should be on or after today");

	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @throws ValidationException
	 */
	public static void validateEndDate(LocalDate startDate, LocalDate endDate) throws ValidationException {

		if (endDate == null)
			throw new ValidationException("End Date should not be empty");

		if (endDate.isBefore(startDate) || endDate.isBefore(LocalDate.now()))
			throw new ValidationException("End Date should be on or after Start Date");

	}

	/**
	 * 
	 * @param reason
	 * @throws ValidationException
	 */
	public static void validateReason(String reason) throws ValidationException {

		String regex = "^[A-Za-z0-9\\s\\-:;.]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(reason.trim());
		if (matcher.matches() == false)
			throw new ValidationException("Only alphanumeric characters and spaces are allowed");

	}

	public static void checkLeaveDateExist(int employeeId, LocalDate startDate, LocalDate endDate)
			throws ValidationException {

		List<Request> datesList = new ArrayList<>();

		try {

			datesList = new RequestService().getAllLeaveDateByEmployeeId(employeeId);

			for (Request request : datesList) {
				
				if (request.getStartDate().isEqual(startDate) || request.getStartDate().isEqual(endDate)
						|| request.getEndDate().isEqual(startDate) || request.getEndDate().isEqual(endDate)
						|| (request.getStartDate().isBefore(startDate) && request.getStartDate().isAfter(endDate))
						|| (request.getStartDate().isAfter(startDate) && request.getEndDate().isBefore(endDate))
						|| (request.getEndDate().isBefore(startDate) && request.getEndDate().isAfter(endDate)))
					throw new ValidationException("You have been already applied a leave request in these days");

			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

}
