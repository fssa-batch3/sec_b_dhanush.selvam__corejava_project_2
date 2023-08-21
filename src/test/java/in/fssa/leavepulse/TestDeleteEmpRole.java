package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.EmployeeRoleService;

public class TestDeleteEmpRole {

	@Test
	public void testDeleteEmployeeWithValidEmpRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		assertDoesNotThrow(() -> {
			empRoleService.delete(4);
		});
	}
	
	@Test
	public void testDeleteEmployeeWithInvalidEmpRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.delete(0);
		});
		String expectedMessage = "Invalid Employee-Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testDeleteEmployeeWithNotExistEmpRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.delete(8);
		});
		String expectedMessage = "Employee-Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
}
