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
	public List<Request> getAll() throws ServiceException {

		try {
			RequestDAO requestDao = new RequestDAO();
			return requestDao.getAll();
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
			RequestDAO requestDao = new RequestDAO();
			RequestValidator.validateRequestId(requestId);
			return requestDao.findRequestByRequestId(requestId);
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
			RequestDAO requestDao = new RequestDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.checkLeaveIdExist(leaveId);
			return requestDao.findRequestByLeaveId(leaveId);
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
			RequestDAO requestDao = new RequestDAO();
			EmployeeValidator.validateManagerId(managerId);
			EmployeeValidator.checkManagerIdExist(managerId);
			return requestDao.findRequestByLeaveId(managerId);
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
	public void create(Request request) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDao = new RequestDAO();
			RequestValidator.validate(request);
			LeaveValidator.validateLeaveId(request.getLeaveId());
			EmployeeValidator.validateEmployeeId(request.getCreatedBy());
			EmployeeValidator.validateManagerId(request.getManagerId());
			LeaveValidator.checkLeaveIdExist(request.getLeaveId());
			EmployeeValidator.checkEmployeeIdExist(request.getCreatedBy());
			EmployeeValidator.checkManagerIdExist(request.getManagerId());
			requestDao.create(request);
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
	public void update(int requestId, Request request) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDao = new RequestDAO();
			RequestValidator.validateRequestId(requestId);
			RequestValidator.validate(request);
			EmployeeValidator.validateManagerId(request.getModifiedBy());
			RequestValidator.checkRequestIdExist(requestId);
			EmployeeValidator.checkManagerIdExist(request.getModifiedBy());
			requestDao.update(requestId, request);
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
	public void delete(int requestId) throws ServiceException, ValidationException { 
		
		try {
			RequestDAO requestDao = new RequestDAO();
			RequestValidator.validateRequestId(requestId);
			RequestValidator.checkRequestIdExist(requestId);
			requestDao.delete(requestId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

}
