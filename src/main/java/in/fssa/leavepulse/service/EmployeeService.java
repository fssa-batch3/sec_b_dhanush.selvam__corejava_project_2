package in.fssa.leavepulse.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.dto.EmployeeDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ServiceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.util.PasswordUtil;
import in.fssa.leavepulse.validator.EmployeeValidator;
import in.fssa.leavepulse.validator.RoleValidator;

public class EmployeeService {

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<Employee> getAllEmployee() throws ServiceException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			return employeeDAO.getAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public List<Integer> getAllEmployeeId() throws ServiceException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			return employeeDAO.getAllId();
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
			EmployeeDAO employeeDAO = new EmployeeDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			return employeeDAO.findEmployeeByEmployeeId(employeeId);
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
			EmployeeDAO employeeDAO = new EmployeeDAO();
			EmployeeValidator.validateEmail(email);
			return employeeDAO.findEmployeeByEmployeeEmail(email);
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
			EmployeeDAO employeeDAO = new EmployeeDAO();
			EmployeeValidator.validatePhoneNo(phoneNo);
			return employeeDAO.findEmployeeByEmployeePhoneNo(phoneNo);
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
	public void createEmployee(Employee employee, int managerId, int roleId)
			throws ServiceException, ValidationException {

		int generatedId = -1;
		List<Leave> leavesList = null;

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			EmployeeValidator.validateEmployee(employee);
			EmployeeValidator.validateFirstName(employee.getFirstName());
			EmployeeValidator.validateLastName(employee.getLastName());
			EmployeeValidator.validateEmail(employee.getEmail());
			EmployeeValidator.validatePhoneNo(employee.getPhoneNo());
			EmployeeValidator.validatePassword(employee.getPassword());
			EmployeeValidator.validateAddress(employee.getAddress());
			EmployeeValidator.validateManagerId(managerId);
			RoleValidator.validateRoleId(roleId);
			EmployeeValidator.checkEmployeeEmailExist(employee.getEmail());
			EmployeeValidator.checkEmployeePhoneNoExist(employee.getPhoneNo());
			EmployeeValidator.checkManagerIdIs(managerId);
			RoleValidator.checkRoleIdIs(roleId);
			employee.setPassword(PasswordUtil.encryptPassword(employee.getPassword()));
			generatedId = employeeDAO.create(employee);
			new EmployeeRoleService().createEmpRole(generatedId, managerId, roleId);
			leavesList = new LeaveService().getAllLeave();
			if (leavesList != null) {
				LeaveBalanceService leaveBalService = new LeaveBalanceService();
				for (Leave leave : leavesList) {
					leaveBalService.createLeaveBalance(generatedId, leave);
				}
			}
			
		} catch (PersistenceException | NoSuchAlgorithmException e) {
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
	public void updateEmployee(int employeeId, Employee employee) throws ServiceException, ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.validateEmployee(employee);
			EmployeeValidator.validateFirstName(employee.getFirstName());
			EmployeeValidator.validateLastName(employee.getLastName());
			EmployeeValidator.validatePhoneNo(employee.getPhoneNo());
			EmployeeValidator.validateAddress(employee.getAddress());
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			EmployeeValidator.checkEmployeePhoneNoExistWithEmployeeId(employeeId, employee.getPhoneNo());
			employeeDAO.update(employeeId, employee);
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
	public void deleteEmployee(int employeeId) throws ServiceException, ValidationException {

		List<Integer> leaveBalIdList = null;
		
		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			EmployeeValidator.validateEmployeeId(employeeId);
			EmployeeValidator.checkEmployeeIdIs(employeeId);
			EmployeeRoleService empRoleService = new EmployeeRoleService();
			int empRoleId = empRoleService.findEmpRoleByEmployeeId(employeeId).getEmpRoleId();
			employeeDAO.delete(employeeId);
			empRoleService.deleteEmpRole(empRoleId);
			LeaveBalanceService leaveBalService = new LeaveBalanceService();
			leaveBalIdList = leaveBalService.findAllLeaveBalanceIdByEmployeeId(employeeId);
			for (int id : leaveBalIdList) {
				leaveBalService.deleteLeaveBalance(id);
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public int login(String email, String password) throws ServiceException, ValidationException {

		try {
			EmployeeValidator.validateEmail(email);
			EmployeeValidator.validatePassword(password);
			EmployeeValidator.checkEmployeeEmailIs(email);
			
			EmployeeDAO employeeDAO = new EmployeeDAO();
			Employee employee = employeeDAO.findEmployeeByEmployeeEmail(email);
			
			String hashPassword = employee.getPassword();
			int firstIndex = hashPassword.indexOf('$'); // Find the index of the first "$"
			int secondIndex = hashPassword.indexOf('$', firstIndex + 1); // Find the index of the second "$" starting
																			// from the position after the first one
			if (firstIndex != -1 && secondIndex != -1) {
				String salt = hashPassword.substring(firstIndex + 1, secondIndex); // Extract the substring between the
																					// two "$" characters
				password = PasswordUtil.checkPass(password, salt);
				String genPass = "$" + salt + "$" + password;
				
				return employeeDAO.passwordChecker(email, genPass);
				
			} else {
				System.out.println("The input string does not contain the expected format.");
				return 0;
			}

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public int getTableLastEmployeeId() throws ServiceException {

		try {
			return new EmployeeDAO().getTableLastEmployeeId();

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<EmployeeDTO> getAllEmployeeWithRole() throws ServiceException {

		try {
			return new EmployeeDAO().getAllEmployeeWithRole();

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException 
	 */
	public List<EmployeeDTO> getAllEmployeeWithRoleByManagerId(int managerId) throws ServiceException, ValidationException {

		try {
			EmployeeValidator.validateEmployeeId(managerId);
			return new EmployeeDAO().getAllEmployeeWithRoleByManagerId(managerId);

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
	public EmployeeDTO findEmployeeWithRole(int employeeId) throws ServiceException, ValidationException {

		try {
			EmployeeValidator.validateEmployeeId(employeeId);
			return new EmployeeDAO().findEmployeeWithRole(employeeId);

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
