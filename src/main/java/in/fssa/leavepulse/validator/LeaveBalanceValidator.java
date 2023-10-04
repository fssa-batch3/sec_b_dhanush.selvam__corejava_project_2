package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.dao.LeaveBalanceDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.util.StringUtil;

public class LeaveBalanceValidator {

	public static void validateLeaveBaIanced(int leaveBalId) throws ValidationException {

		StringUtil.rejectIfInvalidId(leaveBalId, "Leave-Balance Id");

	}

	public static void checkLeaveBalanceIdIs(int leaveBalId) throws ValidationException {

		try {
			LeaveBalanceDAO leaveBalDAO = new LeaveBalanceDAO();
			if (leaveBalDAO.checkLeaveBalanceIs(leaveBalId) == 0)
				throw new ValidationException("Leave-Balance Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}
	
	public static void validateDays (int days) throws ValidationException {

		StringUtil.rejectIfInvalidId(days, "Days count");

	}
				
}
