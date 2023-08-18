package in.fssa.leavepulse.service;

import java.util.List;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.dao.EmployeeRoleDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.EmployeeRole;
import in.fssa.leavepulse.validator.EmployeeRoleValidator;

public class EmployeeRoleService {

	private EmployeeDAO employeeDao;
	private EmployeeRoleDAO employeeRoleDao;

	public EmployeeRoleService() {
		this.employeeRoleDao = new EmployeeRoleDAO();
	}

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<EmployeeRole> getAll() throws ServiceException {

		try {
			return employeeRoleDao.getAll();
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
	public EmployeeRole findEmployeeRoleByEmpRoleId(int empRoleId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleValidator.validateEmployeeRoleId(empRoleId);
			return employeeRoleDao.findEmployeeRoleByEmpRoleId(empRoleId);
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
	public EmployeeRole findEmployeeRoleByEmployeeId(int employeeId) throws ServiceException, ValidationException {

		try {
			EmployeeRoleValidator.validateEmployeeId(employeeId);
			employeeDao = new EmployeeDAO();
			if (employeeDao.findEmployeeByEmployeeId(employeeId) == null)
				throw new ServiceException("Employee Id is not exist in the table");
			return employeeRoleDao.findEmployeeRoleByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
