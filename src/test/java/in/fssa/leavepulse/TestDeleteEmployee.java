package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.EmployeeService;

public class TestDeleteEmployee {
	
	@Test
	public void testDeleteEmployeeWithValidEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		int employeeId = new EmployeeDAO().getLastEmployeeId();
		assertDoesNotThrow(() -> {
			employeeService.deleteEmployee(employeeId);
		});
	}
	
	@Test
	public void testDeleteEmployeeWithInvalidEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.deleteEmployee(0);
		});
		String expectedMessage = "Invalid Employee Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testDeleteEmployeeWithNotExistEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.deleteEmployee(500);
		});
		String expectedMessage = "Employee Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
