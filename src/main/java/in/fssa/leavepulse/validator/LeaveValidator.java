package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.dao.LeaveDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.util.StringUtil;

public class LeaveValidator {
	
	/**
	 * 
	 * @param leave
	 * @throws ValidationException
	 */
	public static void validateLeave(Leave leave) throws ValidationException {

		if (leave == null)
			throw new ValidationException("Leave cannot be null");
		validateLeaveName(leave.getLeaveType());

	}
	
	/**
	 * 
	 * @param leaveId
	 * @throws ValidationException
	 */
	public static void validateLeaveId(int leaveId) throws ValidationException {
		
		StringUtil.rejectIfInvalidId(leaveId, "Leave Id");
		
	}
	
	/**
	 * 
	 * @param leaveId
	 * @throws ValidationException
	 */
	public static void checkLeaveIdExist(int leaveId) throws ValidationException {
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			if (leaveDAO.findLeaveByLeaveId(leaveId) == null)
				throw new ValidationException("Leave Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}
	

	/**
	 * 
	 * @param leaveType
	 * @throws ValidationException
	 */
	public static void validateLeaveName(String leaveName) throws ValidationException {

		StringUtil.rejectIfInvalidString(leaveName, "Leave Type");
		StringUtil.rejectIfInvalidName(leaveName, "Leave Type");
	
	}
	
	/**
	 * 
	 * @param leaveName
	 * @throws ValidationException
	 */
	public static void checkLeaveNameExist(String leaveName) throws ValidationException {
		
		try {
			LeaveDAO leaveDAO = new LeaveDAO();
			if (leaveDAO.findLeaveByLeaveType(leaveName) != null)
				throw new ValidationException("Leave Type already exist");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}
	
}
