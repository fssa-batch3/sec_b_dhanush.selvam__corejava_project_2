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
	public List<Leave> getAll() throws ServiceException {
		
		try {
			LeaveDAO leaveDao = new LeaveDAO();
			return leaveDao.getAll();
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
			LeaveDAO leaveDao = new LeaveDAO();
			LeaveValidator.validateLeaveId(leaveId);
			return leaveDao.findLeaveByLeaveId(leaveId);
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
			LeaveDAO leaveDao = new LeaveDAO();
			LeaveValidator.validateLeaveName(leaveType);
			return leaveDao.findLeaveByLeaveType(leaveType);
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
	public void create(Leave leave) throws ServiceException, ValidationException {
		
		try {
			LeaveDAO leaveDao = new LeaveDAO();
			LeaveValidator.validate(leave);
			LeaveValidator.checkLeaveNameExist(leave.getLeaveType());
			leaveDao.create(leave);
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
	public void update(int leaveId, Leave leave) throws ServiceException, ValidationException {
		
		try {
			LeaveDAO leaveDao = new LeaveDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.validate(leave);
			LeaveValidator.checkLeaveIdExist(leaveId);
			LeaveValidator.checkLeaveNameExist(leave.getLeaveType());
			leaveDao.update(leaveId, leave);
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
	public void delete(int leaveId) throws ServiceException, ValidationException {
		
		try {
			LeaveDAO leaveDao = new LeaveDAO();
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.checkLeaveIdExist(leaveId);
			leaveDao.delete(leaveId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

}
