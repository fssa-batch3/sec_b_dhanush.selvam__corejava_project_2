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

		StringUtil.rejectIfInvalidString(employee.getFirst_name(), "First Name");
		StringUtil.rejectIfInvalidString(employee.getLast_name(), "Last Name");
		StringUtil.rejectIfInvalidString(employee.getFirst_name(), "First Name");
		StringUtil.rejectIfInvalidString(employee.getEmail(), "Email");
		validateEmail(employee.getEmail());
		validatePhoneNo(employee.getPhone_no());
		StringUtil.rejectIfInvalidString(employee.getPassword(), "Password");
		validatePassword(employee.getPassword());
		StringUtil.rejectIfInvalidString(employee.getAddress(), "Address");

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
	public static void checkEmployeeIdExist(int employeeId) throws ValidationException {

		try {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			System.out.println(employeeDAO.findEmployeeByEmployeeId(employeeId));
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
	public static void checkManagerIdExist(int employeeId) throws ValidationException {

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
				throw new ValidationException("Email ID alreay exist");
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
				throw new ValidationException("Phone Number alreay exist");
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

}
