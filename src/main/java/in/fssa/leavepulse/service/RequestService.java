package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.RequestDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Request;
import in.fssa.leavepulse.validator.EmployeeValidator;
import in.fssa.leavepulse.validator.LeaveValidator;
import in.fssa.leavepulse.validator.RequestValidator;

public class RequestService {

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Request> getAllRequest() throws ServiceException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			return requestDAO.getAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param requestId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Request findRequestByRequestId(int requestId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			RequestValidator.validateRequestId(requestId);
			return requestDAO.findRequestByRequestId(requestId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param leaveId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Request findRequestByLeaveId(int leaveId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.checkLeaveIdExist(leaveId);
			return requestDAO.findRequestByLeaveId(leaveId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param managerId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Request findRequestByManagerId(int managerId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			EmployeeValidator.validateManagerId(managerId);
			EmployeeValidator.checkManagerIdExist(managerId);
			return requestDAO.findRequestByLeaveId(managerId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param request
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void createRequest(Request request) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			RequestValidator.validateRequest(request);
			LeaveValidator.validateLeaveId(request.getLeaveId());
			EmployeeValidator.validateEmployeeId(request.getCreatedBy());
			EmployeeValidator.validateManagerId(request.getManagerId());
			LeaveValidator.checkLeaveIdExist(request.getLeaveId());
			EmployeeValidator.checkEmployeeIdExist(request.getCreatedBy());
			EmployeeValidator.checkManagerIdExist(request.getManagerId());
			requestDAO.create(request);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param requestId
	 * @param request
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void updateRequest(int requestId, Request request) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			RequestValidator.validateRequestId(requestId);
			RequestValidator.validateRequest(request);
			EmployeeValidator.validateManagerId(request.getModifiedBy());
			RequestValidator.checkRequestIdExist(requestId);
			EmployeeValidator.checkManagerIdExist(request.getModifiedBy());
			requestDAO.update(requestId, request);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param requestId
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void deleteRequest(int requestId) throws ServiceException, ValidationException { 
		
		try {
			RequestDAO requestDAO = new RequestDAO();
			RequestValidator.validateRequestId(requestId);
			RequestValidator.checkRequestIdExist(requestId);
			requestDAO.delete(requestId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

}