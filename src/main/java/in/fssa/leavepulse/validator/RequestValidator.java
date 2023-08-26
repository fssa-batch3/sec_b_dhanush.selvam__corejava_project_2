package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.dao.RequestDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Request;
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
	
}
