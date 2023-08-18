package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.EmployeeService;

public class TestDeleteEmployee {
	
	@Test
	public void testDeleteEmployeeWithValidId() {
		EmployeeService employeeService = new EmployeeService();
		assertDoesNotThrow(() -> {
			employeeService.delete(5);
		});
	}
	
	@Test
	public void testDeleteEmployeeWithInvalidId() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.delete(0);
		});
		String expectedMessage = "Employee Id is invalid";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testDeleteEmployeeWithNotExistEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.delete(6);
		});
		String expectedMessage = "Employee Id is not exist in the table";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
