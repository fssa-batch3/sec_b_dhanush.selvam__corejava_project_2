package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.RoleDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Role;
import in.fssa.leavepulse.validator.RoleValidator;

public class RoleService {

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> getAllRole() throws ServiceException {

		try {
			RoleDAO roleDAO = new RoleDAO();
			return roleDAO.getAll();
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
			RoleDAO roleDAO = new RoleDAO();
			RoleValidator.validateRoleId(roleId);
			return roleDAO.findRoleByRoleId(roleId);
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
			RoleDAO roleDAO = new RoleDAO();
			RoleValidator.validateRoleName(roleName);
			return roleDAO.findRoleByRoleName(roleName);
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
	public void createRole(Role role) throws ServiceException, ValidationException {

		try {
			RoleDAO roleDAO = new RoleDAO();
			RoleValidator.validateRole(role);
			RoleValidator.checkRoleNameExist(role.getRoleName());
			roleDAO.create(role);
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
	public void updateRole(int roleId, Role role) throws ServiceException, ValidationException {
		
		try {
			RoleDAO roleDAO = new RoleDAO();
			RoleValidator.validateRoleId(roleId);
			RoleValidator.validateRole(role);
			RoleValidator.checkRoleIdExist(roleId);
			RoleValidator.checkRoleNameExist(role.getRoleName());
			roleDAO.update(roleId, role);
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
	public void deleteRole (int roleId) throws ServiceException, ValidationException {
		
		try {
			RoleDAO roleDAO = new RoleDAO();
			RoleValidator.validateRoleId(roleId);
			RoleValidator.checkRoleIdExist(roleId);
			roleDAO.delete(roleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
