package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.LeaveDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.validator.LeaveValidator;

public class LeaveService {
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Leave> getAllLeave() throws ServiceException {
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			return leaveDAO.getAll();
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
	public Leave findLeaveByLeaveId(int leaveId) throws ServiceException, ValidationException {
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			LeaveValidator.validateLeaveId(leaveId);
			return leaveDAO.findLeaveByLeaveId(leaveId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param leaveType
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Leave findLeaveByLeaveName(String leaveType) throws ServiceException, ValidationException {
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			LeaveValidator.validateLeaveName(leaveType);
			return leaveDAO.findLeaveByLeaveType(leaveType);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param leave
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void createLeave(Leave leave) throws ServiceException, ValidationException {
		
		List<Integer> employeesIdList = null;
		Leave newLeave = null;
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			LeaveValidator.validateLeave(leave);
			LeaveValidator.validateLeaveName(leave.getLeaveType());
			LeaveValidator.validateLeaveDays(leave.getLeaveDays());
			LeaveValidator.checkLeaveNameExist(leave.getLeaveType());			
			newLeave = leaveDAO.create(leave);
			employeesIdList = new EmployeeService().getAllEmployeeId();
			if (employeesIdList != null)
				for (Integer id : employeesIdList) {
					new LeaveBalanceService().createLeaveBalance(id, newLeave);
				}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param leaveId
	 * @param leave
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void updateLeave(int leaveId, Leave leave) throws ServiceException, ValidationException {
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.validateLeave(leave);
			LeaveValidator.validateLeaveName(leave.getLeaveType());
			LeaveValidator.validateLeaveDays(leave.getLeaveDays());
			LeaveValidator.checkLeaveIdIs(leaveId);
			LeaveValidator.checkLeaveNameExistWhileUpdating(leaveId, leave.getLeaveType());
			leaveDAO.update(leaveId, leave);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param leaveId
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void deleteLeave(int leaveId) throws ServiceException, ValidationException {
		
		List<Integer> leaveBalIdList = null;
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.checkLeaveIdIs(leaveId);
			leaveDAO.delete(leaveId);
			LeaveBalanceService leaveBalService = new LeaveBalanceService();
			leaveBalIdList = leaveBalService.findAllLeaveBalanceIdByLeaveId(leaveId);
			for (int id : leaveBalIdList) {
				leaveBalService.deleteLeaveBalance(id);
			}
			
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
	public int getTableLastLeaveId() throws ServiceException {
		
		try {
			return new LeaveDAO().getTableLastLeaveId();
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
			
		}
		
	}

}
