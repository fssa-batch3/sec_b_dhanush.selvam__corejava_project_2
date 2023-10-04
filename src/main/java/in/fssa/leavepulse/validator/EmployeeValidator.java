package in.fssa.leavepulse.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.util.StringUtil;

public class EmployeeValidator {

	/**
	 * 
	 * @param employee
	 * @throws ValidationException
	 */
	public static void validateEmployee(Employee employee) throws ValidationException {

		if (employee == null)
			throw new ValidationException("Employee cannot be null");
		
	}

	/**
	 * 
	 * @param employeeId
	 * @throws ValidationException
	 */
	public static void validateEmployeeId(int employeeId) throws ValidationException {

		StringUtil.rejectIfInvalidId(employeeId, "Employee Id");

	}

	/**
	 * 
	 * @param employeeId
	 * @throws ValidationException
	 */
	public static void checkEmployeeIdIs(int employeeId) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			if (employeeDAO.findEmployeeByEmployeeId(employeeId) == null)
				throw new ValidationException("Employee Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param employeeId
	 * @throws ValidationException
	 */
	public static void validateManagerId(int managerId) throws ValidationException {

		StringUtil.rejectIfInvalidId(managerId, "Manager Id");

	}
	
	/**
	 * 
	 * @param employeeId
	 * @throws ValidationException
	 */
	public static void checkManagerIdIs(int employeeId) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			if (employeeDAO.findEmployeeByEmployeeId(employeeId) == null)
				throw new ValidationException("Manager Id not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";

	/**
	 * 
	 * @param email
	 * @throws ValidationException
	 */
	public static void validateEmail(String email) throws ValidationException {

		StringUtil.rejectIfInvalidString(email, "Email");

		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw new ValidationException("Invalid Email Id");
		}

	}

	/**
	 * 
	 * @param employeeEmail
	 * @throws ValidationException
	 */
	public static void checkEmployeeEmailExist(String employeeEmail) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			if (employeeDAO.findEmployeeByEmployeeEmail(employeeEmail) != null)
				throw new ValidationException("Email ID already exist");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}
	
	public static void checkEmployeeEmailIs(String employeeEmail) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			if (employeeDAO.findEmployeeByEmployeeEmail(employeeEmail) == null)
				throw new ValidationException("Email ID not found");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param phoneNo
	 * @throws ValidationException
	 */
	public static void validatePhoneNo(long phoneNo) throws ValidationException {

		if (phoneNo < 6000000001l || phoneNo > 9999999999l)
			throw new ValidationException("Invalid Phone Number");

	}
	
	/**
	 * 
	 * @param employeePhoneNo
	 * @throws ValidationException
	 */
	public static void checkEmployeePhoneNoExist(long employeePhoneNo) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			if (employeeDAO.findEmployeeByEmployeePhoneNo(employeePhoneNo) != null)
				throw new ValidationException("Phone Number already exist");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

	public static void checkEmployeePhoneNoExistWithEmployeeId(int employeeId, long employeePhoneNo) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			Employee employee = employeeDAO.findEmployeeByEmployeePhoneNo(employeePhoneNo);
			if (employee != null && employee.getEmployeeId() != employeeId)
				throw new ValidationException("Phone Number already exist");
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}
	
	private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{8,24}";

	/**
	 * 
	 * @param password
	 * @throws ValidationException
	 */
	public static void validatePassword(String password) throws ValidationException {

		StringUtil.rejectIfInvalidString(password, "Password");

		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);

		if (!matcher.matches())
			throw new ValidationException("Password doesn't match the pattern");

	}
	
	/**
	 * 
	 * @param address
	 * @throws ValidationException
	 */
	public static void validateAddress(String address) throws ValidationException {

		StringUtil.rejectIfInvalidString(address, "Address");

		String regex = "^[A-Za-z0-9\\s.,#/-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(address);
		if (matcher.matches() == false) {
			throw new ValidationException("Invalid Address Pattern");
		}
	}
	
	/**
	 * 
	 * @param firstName
	 * @throws ValidationException
	 */
	public static void validateFirstName(String firstName) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(firstName, "First Name");
		StringUtil.rejectIfInvalidName(firstName, "First Name");

	}
	
	/**
	 * 
	 * @param lastName
	 * @throws ValidationException
	 */
	public static void validateLastName(String lastName) throws ValidationException {
		
		StringUtil.rejectIfInvalidString(lastName, "Last Name");
		StringUtil.rejectIfInvalidName(lastName, "Last Name");

	}

}
