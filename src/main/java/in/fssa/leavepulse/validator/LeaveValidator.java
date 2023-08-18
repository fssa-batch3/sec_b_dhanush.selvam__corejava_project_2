package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.util.StringUtil;

public class LeaveValidator {
	
	/**
	 * 
	 * @param leave
	 * @throws ValidationException
	 */
	public static void validate(Leave leave) throws ValidationException {

		if (leave == null)
			throw new ValidationException("Leave cannot be null");
		validateLeaveType(leave.getLeaveType());

	}

	/**
	 * 
	 * @param leaveType
	 * @throws ValidationException
	 */
	public static void validateLeaveType(String leaveType) throws ValidationException {

		StringUtil.rejectIfInvalidString(leaveType, "Leave Name");

	}
	
	/**
	 * 
	 * @param leaveId
	 * @throws ValidationException
	 */
	public static void validateLeaveId(int leaveId) throws ValidationException {
		
		StringUtil.rejectIfInvalidId(leaveId, "Leave Id");
		
	}

}
