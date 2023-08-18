package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.dao.RoleDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Role;
import in.fssa.leavepulse.util.StringUtil;

public class RoleValidator {
	
	private static RoleDAO roleDao;

	/**
	 * 
	 * @param role
	 * @throws ValidationException
	 */
	public static void validate(Role role) throws ValidationException {

		if (role == null)
			throw new ValidationException("Role cannot be null");
		validateRoleName(role.getRoleName());

	}

	/**
	 * 
	 * @param roleId
	 * @throws ValidationException
	 */
	public static void validateRoleId(int roleId) throws ValidationException {

		StringUtil.rejectIfInvalidId(roleId, "Role Id");

	}
	
//	public static void checkRoleIdExist(int roleId) throws ValidationException {
//		
//		
//		
//	}

	/**
	 * 
	 * @param roleName
	 * @throws ValidationException
	 */
	public static void validateRoleName(String roleName) throws ValidationException {

		StringUtil.rejectIfInvalidString(roleName, "Role Name");

	}

	/**
	 * 
	 * @param roleName
	 * @throws ValidationException
	 */
	public static void checkRoleNameExist(String roleName) throws ValidationException {

		roleDao = new RoleDAO();
		try {
			if (roleDao.findRoleByRoleName(roleName) != null)
				throw new ValidationException("Role Name already exist");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

}
