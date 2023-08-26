package in.fssa.leavepulse.validator;

import in.fssa.leavepulse.dao.RoleDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Role;
import in.fssa.leavepulse.util.StringUtil;

public class RoleValidator {

	/**
	 * 
	 * @param role
	 * @throws ValidationException
	 */
	public static void validateRole(Role role) throws ValidationException {

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

	/**
	 * 
	 * @param roleId
	 * @throws ValidationException
	 */
	public static void checkRoleIdExist(int roleId) throws ValidationException {

		try {
			RoleDAO roleDAO = new RoleDAO();
			if (roleDAO.findRoleByRoleId(roleId) == null)
				throw new ValidationException("Role Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

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

		try {
			RoleDAO roleDAO = new RoleDAO();
			if (roleDAO.findRoleByRoleName(roleName) != null)
				throw new ValidationException("Role Name already exist");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

}
