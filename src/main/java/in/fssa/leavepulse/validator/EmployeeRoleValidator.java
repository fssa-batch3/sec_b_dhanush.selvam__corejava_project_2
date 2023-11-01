package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.dao.EmployeeRoleDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException; 
import in.fssa.leavepulse.model.EmployeeRole;
import in.fssa.leavepulse.util.StringUtil;

public class EmployeeRoleValidator {

	/**
	 * 
	 * @param empRole
	 * @throws ValidationException
	 */
	public static void validateEmpRole(EmployeeRole empRole) throws ValidationException {

		if (empRole == null)
			throw new ValidationException("Employee-Role cannot be null");
			EmployeeValidator.validateManagerId(empRole.getManagerId());
			RoleValidator.validateRoleId(empRole.getRoleId());

	}
	
	/**
	 * 
	 * @param employeeRoleId
	 * @throws ValidationException
	 */
	public static void validateEmpRoleId(int employeeRoleId) throws ValidationException {

		StringUtil.rejectIfInvalidId(employeeRoleId, "Employee-Role Id");

	}
	
	/**
	 * 
	 * @param empRoleId
	 * @throws ValidationException
	 */
	public static void checkEmpRoleIdIs(int empRoleId) throws ValidationException {
		
		try {
			EmployeeRoleDAO empRoleDAO = new EmployeeRoleDAO();
			if (empRoleDAO.findEmpRoleByEmpRoleId(empRoleId) == null)
				throw new ValidationException("Employee-Role Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		
	}

}
