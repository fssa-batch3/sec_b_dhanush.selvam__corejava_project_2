package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.EmployeeRole;
import in.fssa.leavepulse.util.StringUtil;

public class EmployeeRoleValidator {

	/**
	 * 
	 * @param empRole
	 * @throws ValidationException
	 */
	public static void validate(EmployeeRole empRole) throws ValidationException {

		if (empRole == null)
			throw new ValidationException("EmployeeRole cannot be null");
		validateEmployeeId(empRole.getEmployeeId());
		validateManagerId(empRole.getManagerId());
		validateRoleId(empRole.getRoleId());

	}
	
	/**
	 * 
	 * @param employeeRoleId
	 * @throws ValidationException
	 */
	public static void validateEmployeeRoleId(int employeeRoleId) throws ValidationException {

		StringUtil.rejectIfInvalidId(employeeRoleId, "Employee Role Id");

	}

	/**
	 * 
	 * @param employeeId
	 * @throws ValidationException
	 */
	public static void validateEmployeeId(int employeeId) throws ValidationException {

		StringUtil.rejectIfInvalidId(employeeId, "Employee Id");

	}

	/**
	 * 
	 * @param employeeId
	 * @throws ValidationException
	 */
	public static void validateManagerId(int employeeId) throws ValidationException {

		StringUtil.rejectIfInvalidId(employeeId, "Manager Id");

	}
	
	/**
	 * 
	 * @param roleId
	 * @throws ValidationException
	 */
	public static void validateRoleId(int roleId) throws ValidationException {

		StringUtil.rejectIfInvalidId(roleId, "Role Id");

	}

}
