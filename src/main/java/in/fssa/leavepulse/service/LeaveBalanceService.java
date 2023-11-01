package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.LeaveBalanceDAO;
import in.fssa.leavepulse.dto.LeaveBalanceDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.model.LeaveBalance;
import in.fssa.leavepulse.validator.EmployeeValidator;
import in.fssa.leavepulse.validator.LeaveBalanceValidator;
import in.fssa.leavepulse.validator.LeaveValidator;

public class LeaveBalanceService {
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public List<Integer> findAllLeaveBalanceIdByEmployeeId(int employeeId) throws ServiceException, ValidationException {
		
		List<Integer> leaveBalIdList = null;
		
		try {
			LeaveBalanceDAO leaveBalDAO = new  LeaveBalanceDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			leaveBalIdList = leaveBalDAO.findAllLeaveBalanceIdByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return leaveBalIdList;
	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public List<LeaveBalanceDTO> findAllLeaveBalanceByEmployeeId(int employeeId) throws ServiceException, ValidationException {
		
		List<LeaveBalanceDTO> leaveBalList = null;
		
		try {
			LeaveBalanceDAO leaveBalDAO = new  LeaveBalanceDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			leaveBalList = leaveBalDAO.findAllLeaveBalanceByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return leaveBalList;
	}
	
	/**
	 * 
	 * @param leaveId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public List<Integer> findAllLeaveBalanceIdByLeaveId(int leaveId) throws ServiceException, ValidationException {
		
		List<Integer> leaveBalIdList = null;
		
		try {
			LeaveBalanceDAO leaveBalDAO = new  LeaveBalanceDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.checkLeaveIdIs(leaveId);
			leaveBalIdList = leaveBalDAO.findAllLeaveBalanceIdByLeaveId(leaveId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return leaveBalIdList;
	}
	
	/**
	 * 
	 * @param employeeId
	 * @param leave
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void createLeaveBalance(int employeeId, Leave leave) throws ServiceException, ValidationException {
		
		try {
			LeaveBalanceDAO leaveBalDAO = new  LeaveBalanceDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			LeaveValidator.validateLeave(leave);
			LeaveValidator.validateLeaveId(leave.getLeaveId());
			LeaveValidator.validateLeaveDays(leave.getLeaveDays());
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			LeaveValidator.checkLeaveIdIs(leave.getLeaveId());
			leaveBalDAO.create(employeeId, leave);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param type
	 * @param employeeId
	 * @param leaveId
	 * @param days
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void updateLeaveBalance(String type, int employeeId, int leaveId, int days) throws ServiceException, ValidationException {
		
		try {
			LeaveBalanceDAO leaveBalDAO = new LeaveBalanceDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			LeaveValidator.validateLeaveId(leaveId);
			LeaveBalanceValidator.validateDays(days);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			LeaveValidator.checkLeaveIdIs(leaveId);
			leaveBalDAO.update(type, employeeId, leaveId, days);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

	/**
	 * 
	 * @param leaveBalId
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void deleteLeaveBalance (int leaveBalId) throws ServiceException, ValidationException {
		
		try {
			LeaveBalanceDAO leaveBalDAO = new  LeaveBalanceDAO();
			LeaveBalanceValidator.validateLeaveBaIanced(leaveBalId);
			LeaveBalanceValidator.checkLeaveBalanceIdIs(leaveBalId);
			leaveBalDAO.delete(leaveBalId);
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param employeeId
	 * @param leaveId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public int remainingLeaveCountOfALeaveType(int employeeId, int leaveId) throws ServiceException, ValidationException {
		
		try {
			LeaveBalanceDAO leaveBalDAO = new  LeaveBalanceDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			LeaveValidator.validateLeaveId(leaveId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			LeaveValidator.checkLeaveIdIs(leaveId);
			return leaveBalDAO.remainingLeaveCountOfALeaveType(employeeId, leaveId);
			
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
	public List<LeaveBalance> findAllAvailableLeavesByEmployeeId(int employeeId) throws ServiceException, ValidationException {
		
		List<LeaveBalance> leaveBalIdList = null;
		
		try {
			LeaveBalanceDAO leaveBalDAO = new  LeaveBalanceDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			leaveBalIdList = leaveBalDAO.findAllAvailableLeavesByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return leaveBalIdList;
	}
	
}
