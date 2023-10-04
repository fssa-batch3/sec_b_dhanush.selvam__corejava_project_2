package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.RequestDAO;
import in.fssa.leavepulse.dto.RequestDTO;
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
	public List<Request> findAllRequestByLeaveId(int leaveId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.checkLeaveIdIs(leaveId);
			return requestDAO.findAllRequestByLeaveId(leaveId);
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
	public List<Request> findAllRequestByManagerId(int managerId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			EmployeeValidator.validateManagerId(managerId);
			EmployeeValidator.checkManagerIdIs(managerId);
			return requestDAO.findAllRequestByManagerId(managerId);
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
			RequestValidator.validateStartDate(request.getStartDate());
			RequestValidator.validateEndDate(request.getStartDate(), request.getEndDate());
			RequestValidator.validateReason(request.getReason());
			LeaveValidator.validateLeaveId(request.getLeaveId());
			EmployeeValidator.validateEmployeeId(request.getCreatedBy());
			EmployeeValidator.validateManagerId(request.getManagerId());
			LeaveValidator.checkLeaveIdIs(request.getLeaveId());
//			RequestValidator.checkLeaveDateExist(request.getCreatedBy(), request.getStartDate(), request.getEndDate());
			EmployeeValidator.checkEmployeeIdIs(request.getCreatedBy());
			EmployeeValidator.checkManagerIdIs(request.getManagerId());
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
			RequestValidator.validateReason(request.getComments());
			RequestValidator.checkRequestIdExist(requestId);
			EmployeeValidator.checkManagerIdIs(request.getModifiedBy());
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
	public void cancelRequest(int requestId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			RequestValidator.validateRequestId(requestId);
			RequestValidator.checkRequestIdExist(requestId);
			requestDAO.cancel(requestId);
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

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<RequestDTO> getAllRequestWithEmployee() throws ServiceException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			return requestDAO.getAllRequestWithEmployee();
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
	public List<RequestDTO> getAllRequestWithEmployeeByManagerId(int managerId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			EmployeeValidator.validateManagerId(managerId);
			EmployeeValidator.checkManagerIdIs(managerId);
			return requestDAO.getAllRequestWithEmployeeByManagerId(managerId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException 
	 */
	public List<RequestDTO> getAllRequestWithEmployeeByEmployeeId(int employeeId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			return requestDAO.getAllRequestWithEmployeeByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public List<Request> getAllLeaveDateByEmployeeId(int employeeId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			return requestDAO.getAllLeaveDateByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
