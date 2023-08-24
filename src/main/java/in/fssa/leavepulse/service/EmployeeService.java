package in.fssa.leavepulse.service;

import java.util.List; 

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.validator.EmployeeValidator;
import in.fssa.leavepulse.validator.RoleValidator;

public class EmployeeService {

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Employee> getAll() throws ServiceException {

		try {
			EmployeeDAO employeeDao = new EmployeeDAO();
			return employeeDao.getAll();
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
	public Employee findEmployeeByEmployeeId(int employeeId) throws ServiceException, ValidationException {

		try {
			EmployeeDAO employeeDao = new EmployeeDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			return employeeDao.findEmployeeByEmployeeId(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Employee findEmployeeByEmployeeEmail(String email) throws ServiceException, ValidationException {

		try {
			EmployeeDAO employeeDao = new EmployeeDAO();
			EmployeeValidator.validateEmail(email);
			return employeeDao.findEmployeeByEmployeeEmail(email);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param phoneNo
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Employee findEmployeeByEmployeePhoneNo(long phoneNo) throws ServiceException, ValidationException {

		try {
			EmployeeDAO employeeDao = new EmployeeDAO();
			EmployeeValidator.validatePhoneNo(phoneNo);
			return employeeDao.findEmployeeByEmployeePhoneNo(phoneNo);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param employee
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void create(Employee employee, int managerId, int roleId) throws ServiceException, ValidationException {

		int generatedId = -1;

		try {
			EmployeeDAO employeeDao = new EmployeeDAO();
			EmployeeValidator.validate(employee);
			EmployeeValidator.validateManagerId(managerId);
			RoleValidator.validateRoleId(roleId);
			EmployeeValidator.checkEmployeeEmailExist(employee.getEmail());
			EmployeeValidator.checkEmployeePhoneNoExist(employee.getPhone_no());
			EmployeeValidator.checkManagerIdExist(managerId);
			RoleValidator.checkRoleIdExist(roleId);
			generatedId = employeeDao.create(employee);
			new EmployeeRoleService().create(generatedId, managerId, roleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param employeeId
	 * @param employee
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void update(int employeeId, Employee employee) throws ServiceException, ValidationException {

		try {
			EmployeeDAO employeeDao = new EmployeeDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.validate(employee);
			EmployeeValidator.checkEmployeeIdExist(employeeId);
			employeeDao.update(employeeId, employee);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param employeeId
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public void delete(int employeeId) throws ServiceException, ValidationException {

		try {
			EmployeeDAO employeeDao = new EmployeeDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdExist(employeeId);
			EmployeeRoleService empRoleService = new EmployeeRoleService();
			int empRoleId = empRoleService.findEmpRoleByEmployeeId(employeeId).getEmpRoleId();
			employeeDao.delete(employeeId);
			empRoleService.delete(empRoleId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
