package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.LeaveDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.validator.LeaveValidator;

public class LeaveService {
	
	private LeaveDAO leaveDao;
	
	public LeaveService() {
		this.leaveDao = new LeaveDAO();
	}
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Leave> getAll() throws ServiceException {
		
		try {
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
			LeaveValidator.validateLeaveType(leaveType);
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
			LeaveValidator.validate(leave);
			if (leaveDao.findLeaveByLeaveType(leave.getLeaveType()) != null)
				throw new ValidationException("Leave Name already exist");
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
			LeaveValidator.validateLeaveId(leaveId);
			LeaveValidator.validate(leave);
			if (leaveDao.findLeaveByLeaveId(leaveId) == null)
				throw new ValidationException("Leave Id is not exist in the table");
			if (leaveDao.findLeaveByLeaveType(leave.getLeaveType()) != null)
				throw new ValidationException("Leave Name already exist");
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
			LeaveValidator.validateLeaveId(leaveId);
			if (leaveDao.findLeaveByLeaveId(leaveId) == null)
				throw new ValidationException("Leave Id is not exist in the table");
			leaveDao.delete(leaveId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

}
