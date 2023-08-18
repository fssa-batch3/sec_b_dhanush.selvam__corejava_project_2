package in.fssa.leavepulse.service;

import java.util.List ;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.validator.EmployeeValidator;

public class EmployeeService {

	private EmployeeDAO employeeDao;
	
	public EmployeeService() {
		this.employeeDao = new EmployeeDAO();
	}
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Employee> getAll() throws ServiceException {
		
		try {
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
	public void create(Employee employee) throws ServiceException, ValidationException {
		
		try {
			EmployeeValidator.validate(employee);
			if (employeeDao.findEmployeeByEmployeeEmail(employee.getEmail()) != null)
				throw new ValidationException("Email ID already exist");
			if (employeeDao.findEmployeeByEmployeePhoneNo(employee.getPhone_no()) != null)
				throw new ValidationException("Phone Number already exist");
			employeeDao.create(employee);
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
	public void update (int employeeId, Employee employee) throws ServiceException, ValidationException {
		
		try {
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.validate(employee);
			if (employeeDao.findEmployeeByEmployeeId(employeeId) == null)
				throw new ValidationException("Employee Id is not exist in the table");
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
	public void delete (int employeeId) throws ServiceException, ValidationException {
		
		try {
			EmployeeValidator.validateEmployeeId(employeeId);
			if (employeeDao.findEmployeeByEmployeeId(employeeId) == null)
				throw new ValidationException("Employee Id is not exist in the table");
			employeeDao.delete(employeeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
}
