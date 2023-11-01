package in.fssa.leavepulse.service;

import java.time.LocalDate;
import java.util.List;

import in.fssa.leavepulse.dao.RequestDAO;
import in.fssa.leavepulse.dto.RequestDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.LeaveBalance;
import in.fssa.leavepulse.model.Notification;
import in.fssa.leavepulse.model.Request;
import in.fssa.leavepulse.util.DateUtil;
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
			RequestValidator.checkLeaveBalanceofLeaveType(request.getStartDate(), request.getEndDate(), request.getLeaveId(), request.getCreatedBy());
			LeaveValidator.checkLeaveIdIs(request.getLeaveId());
			RequestValidator.checkLeaveDateExist(request.getCreatedBy(), request.getStartDate(), request.getEndDate());
			EmployeeValidator.checkEmployeeIdIs(request.getCreatedBy());
			EmployeeValidator.checkManagerIdIs(request.getManagerId());
			
			int days = (int) DateUtil.getDaysWithoutSundays(request.getStartDate(), request.getEndDate());
			
			requestDAO.create(request);
			new LeaveBalanceService().updateLeaveBalance("update", request.getCreatedBy(), request.getLeaveId(), days);
			
			Notification notification = new Notification();
			notification.setSender(request.getCreatedBy());
			notification.setReceiver(request.getManagerId());
			notification.setMessage('N');
			new NotificationService().create(notification);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public void createRequestWithLossOfPay(Request request) throws ServiceException, ValidationException {

		List<LeaveBalance> availableLeaves;
		LeaveBalanceService leaveBalService = new LeaveBalanceService();
		
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
			RequestValidator.checkLeaveDateExist(request.getCreatedBy(), request.getStartDate(), request.getEndDate());
			EmployeeValidator.checkEmployeeIdIs(request.getCreatedBy());
			EmployeeValidator.checkManagerIdIs(request.getManagerId());
			
			availableLeaves = new LeaveBalanceService().findAllAvailableLeavesByEmployeeId(request.getCreatedBy());
			String lastLOPId = new RequestService().findEmployeeLastLossOfPayId(request.getCreatedBy());
			int newLOPId;
			
			if (lastLOPId == null) newLOPId = 1;
			else newLOPId = (Integer.parseInt(lastLOPId) + 1);
			
			if (availableLeaves == null) {
				request.setLossOfPay(request.getCreatedBy() + "" + newLOPId);
				request.setLeaveId(5);
				requestDAO.create(request);
			}
			
			else {
				
				LocalDate startDate = request.getStartDate();
				LocalDate endDate = request.getEndDate();
				int currentIndex = 0;
				
				int days = (int) DateUtil.getDaysWithoutSundays(request.getStartDate(), request.getEndDate());
				
				for (LeaveBalance leave : availableLeaves) {
					request.setLossOfPay(request.getCreatedBy() + "" + newLOPId);
					request.setLeaveId(leave.getLeaveId());
					LocalDate currentEndDate = startDate.plusDays(leave.getAvailableLeaveDays() - 1);
					request.setEndDate(currentEndDate);
					requestDAO.create(request);
					leaveBalService.updateLeaveBalance("update", request.getCreatedBy(), request.getLeaveId(), days);
					startDate = (currentEndDate.plusDays(1));
					request.setStartDate(startDate);
					currentIndex++;
					
					if (currentIndex == availableLeaves.size()) {
						request.setLossOfPay(request.getCreatedBy() + "" + newLOPId);
						request.setLeaveId(8);
						request.setStartDate(request.getEndDate().plusDays(1));
						request.setEndDate(endDate);
						requestDAO.create(request);
						leaveBalService.updateLeaveBalance("cancel", request.getCreatedBy(), request.getLeaveId(), days);
					}
						
				}
			}
			
			Notification notification = new Notification();
			notification.setSender(request.getCreatedBy());
			notification.setReceiver(request.getManagerId());
			notification.setMessage('N');
			new NotificationService().create(notification);
			
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
			RequestValidator.checkRequestIdIs(requestId);
			EmployeeValidator.checkManagerIdIs(request.getModifiedBy());
			requestDAO.update(requestId, request);
			
			if (request.getLeaveStatus().toString() == "Accepted" || request.getLeaveStatus().toString() == "Rejected") {
				Notification notification = new Notification();
				notification.setSender(request.getModifiedBy());
				notification.setReceiver(new RequestService().findRequestByRequestId(requestId).getCreatedBy());
				if (request.getLeaveStatus().toString() == "Accepted")
					notification.setMessage('A');
				else if (request.getLeaveStatus().toString() == "Rejected")
					notification.setMessage('R');
				
				new NotificationService().create(notification);
			}
			
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
			RequestValidator.checkRequestIdIs(requestId);
			requestDAO.cancel(requestId);
			Notification notification = new Notification();
			Request request = new RequestService().findRequestByRequestId(requestId);
			notification.setSender(request.getCreatedBy());
			notification.setReceiver(request.getManagerId());
			notification.setMessage('C');
			new NotificationService().create(notification);
			
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
			RequestValidator.checkRequestIdIs(requestId);
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
	
	public String findEmployeeLastLossOfPayId(int employeeId) throws ServiceException, ValidationException {

		try {
			RequestDAO requestDAO = new RequestDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			return requestDAO.findEmployeeLastLossOfPayId(employeeId);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
