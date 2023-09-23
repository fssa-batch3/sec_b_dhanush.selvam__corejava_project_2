package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.EmployeeService;

public class TestGetAllEmployeeAndGetEmployeeByUniqueValue {

	@Test
	public void testGetAllEmployee() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.getAllEmployee());
		});
	}
	
	@Test
	public void testGetEmployeeByEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.findEmployeeByEmployeeId(3));
		});
	}
	
	@Test
	public void testGetEmployeeByInvalidEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(employeeService.findEmployeeByEmployeeId(0));
		});
		String expectedMessage = "Invalid Employee Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetEmployeeByValidEmail() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.findEmployeeByEmployeeEmail("dhanush@kowmart.com"));
		});
	}
	
	@Test
	public void testGetEmployeeByInvalidEmailNull() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(employeeService.findEmployeeByEmployeeEmail(null));
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetEmployeeByInvalidEmailEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(employeeService.findEmployeeByEmployeeEmail(""));
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetEmployeeByInvalidEmailFormat() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(employeeService.findEmployeeByEmployeeEmail("dhanush@kowmartcom"));
		});
		String expectedMessage = "Invalid Email Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetEmployeeByValidPhoneNo() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.findEmployeeByEmployeePhoneNo(9025214260l));
		});
	}
	
	@Test
	public void testGetEmployeeByInvalidPhoneNo() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(employeeService.findEmployeeByEmployeePhoneNo(902521426l));
		});
		String expectedMessage = "Invalid Phone Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetTableLastEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.getTableLastEmployeeId());
		});
	}
	
	@Test
	public void testGetAllEmployeeWithRole() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.getAllEmployeeWithRole());
		});
	}
	
	@Test
	public void testFindEmployeeWithRole() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.findEmployeeWithRole(2));
		});
	}
	
	@Test
	public void testGetAllEmployeeWithRoleByManagerId() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			System.out.println(employeeService.getAllEmployeeWithRoleByManagerId(2));
		});
	}
}
