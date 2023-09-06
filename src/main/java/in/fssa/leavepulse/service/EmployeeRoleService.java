package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.EmployeeRoleDAO;
import in.fssa.leavepulse.dto.EmployeeRoleDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.EmployeeRole;
import in.fssa.leavepulse.validator.EmployeeRoleValidator;
import in.fssa.leavepulse.validator.EmployeeValidator;
import in.fssa.leavepulse.validator.RoleValidator;

public class EmployeeRoleService {

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<EmployeeRole> getAllEmpRole() throws ServiceException {

		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			return employeeRoleDAO.getAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param empRoleId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public EmployeeRole findEmpRoleByEmpRoleId(int empRoleId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			EmployeeRoleValidator.validateEmpRoleId(empRoleId);
			return employeeRoleDAO.findEmpRoleByEmpRoleId(empRoleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public EmployeeRole findEmpRoleByEmployeeId(int employeeId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdExist(employeeId);
			return employeeRoleDAO.findEmpRoleByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param managerId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public List<EmployeeRole> findAllEmpRoleByManagerId(int managerId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			EmployeeValidator.validateManagerId(managerId);
			EmployeeValidator.checkManagerIdExist(managerId);
			return employeeRoleDAO.findAllEmpRoleByManagerId(managerId);
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
	public List<EmployeeRole> findAllEmpRoleByRoleId(int roleId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			RoleValidator.validateRoleId(roleId);
			RoleValidator.checkRoleIdExist(roleId);
			return employeeRoleDAO.findAllEmpRoleByRoleId(roleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param employeeId
	 * @param managerId
	 * @param roleId
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void createEmpRole(int employeeId, int managerId, int roleId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdExist(employeeId);
			employeeRoleDAO.create(employeeId, managerId, roleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param empRoleId
	 * @param empRole
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void updateEmpRole(int empRoleId, EmployeeRole empRole) throws ServiceException, ValidationException {

		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			EmployeeRoleValidator.validateEmpRoleId(empRoleId);
			EmployeeRoleValidator.validateEmpRole(empRole);
			EmployeeRoleValidator.checkEmpRoleIdExist(empRoleId);
			EmployeeValidator.checkManagerIdExist(empRole.getManagerId());
			RoleValidator.checkRoleIdExist(empRole.getRoleId());
			employeeRoleDAO.update(empRoleId, empRole);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param empRoleId
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void deleteEmpRole(int empRoleId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleDAO empRoleDAO = new EmployeeRoleDAO();
			EmployeeRoleValidator.validateEmpRoleId(empRoleId);
			EmployeeRoleValidator.checkEmpRoleIdExist(empRoleId);
			empRoleDAO.delete(empRoleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public List<EmployeeRoleDTO> getAllEmpRoleWithEmployee() throws ServiceException {
		
		try {
			EmployeeRoleDAO employeeRoleDAO = new EmployeeRoleDAO();
			return employeeRoleDAO.getAllEmpRoleWithEmployee();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

}
