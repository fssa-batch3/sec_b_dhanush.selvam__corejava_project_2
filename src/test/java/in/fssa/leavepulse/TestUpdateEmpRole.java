package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.EmployeeRole;
import in.fssa.leavepulse.service.EmployeeRoleService;

public class TestUpdateEmpRole {
	
	@Test
	public void testUpdateEmpRole() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		EmployeeRole empRole = new EmployeeRole(3, 1, 3);
		assertDoesNotThrow(() -> {
			empRoleService.update(3, empRole);
		});
	}	
	
	@Test
	public void testUpdateEmpRoleByInvalidEmpRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		EmployeeRole empRole = new EmployeeRole(3, 1, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.update(0, empRole);
		});
		String expectedMessage = "Invalid Employee-Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmpRoleByNotExistEmpRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		EmployeeRole empRole = new EmployeeRole(3, 1, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.update(20, empRole);
		});
		String expectedMessage = "Employee-Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmpRoleByInvalidManagerId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		EmployeeRole empRole = new EmployeeRole(3, 0, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.update(3, empRole);
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmpRoleByNotExistManagerId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		EmployeeRole empRole = new EmployeeRole(3, 20, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.update(4, empRole);
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmpRoleByInvalidRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		EmployeeRole empRole = new EmployeeRole(3, 1, 0);
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.update(4, empRole);
		});
		String expectedMessage = "Invalid Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmpRoleByNotExistRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		EmployeeRole empRole = new EmployeeRole(3, 1, 20);
		Exception exception = assertThrows(ValidationException.class, () -> {
			empRoleService.update(4, empRole);
		});
		String expectedMessage = "Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
