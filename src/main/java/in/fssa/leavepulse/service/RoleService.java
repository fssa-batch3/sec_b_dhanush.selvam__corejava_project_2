package in.fssa.leavepulse.service;

import java.util.List;  

import in.fssa.leavepulse.dao.RoleDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Role;
import in.fssa.leavepulse.validator.RoleValidator;

public class RoleService {

	private RoleDAO roleDao;

	public RoleService() {
		this.roleDao = new RoleDAO();
	}

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> getAll() throws ServiceException {

		try {
			return roleDao.getAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Role findRoleByRoleId(int roleId) throws ServiceException, ValidationException {

		try {
			RoleValidator.validateRoleId(roleId);
			return roleDao.findRoleByRoleId(roleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		} 

	}
	/**
	 * 
	 * @param roleName
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Role findRoleByRoleName(String roleName) throws ServiceException, ValidationException {

		try {
			RoleValidator.validateRoleName(roleName);
			return roleDao.findRoleByRoleName(roleName);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param role
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void create(Role role) throws ServiceException, ValidationException {

		try {
			RoleValidator.validate(role);
			RoleValidator.checkRoleNameExist(role.getRoleName());
			roleDao.create(role);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param roleId
	 * @param role
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void update(int roleId, Role role) throws ServiceException, ValidationException {
		
		try {
			RoleValidator.validateRoleId(roleId);
			RoleValidator.validate(role);
			if (roleDao.findRoleByRoleId(roleId) == null)
				throw new ValidationException("Role Id is not exist in the table");
			RoleValidator.checkRoleNameExist(role.getRoleName());
			roleDao.update(roleId, role);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param roleId
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void delete (int roleId) throws ServiceException, ValidationException {
		
		try {
			RoleValidator.validateRoleId(roleId);
			if (roleDao.findRoleByRoleId(roleId) == null)
				throw new ValidationException("Role Id is not exist in the table");
			roleDao.delete(roleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
