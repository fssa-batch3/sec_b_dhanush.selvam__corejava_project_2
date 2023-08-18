package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.RoleService;

public class TestDeleteRole {
	
	@Test
	public void testDeleteRoleWithValidId() {
		RoleService roleService = new RoleService();
		assertDoesNotThrow(() -> {
			roleService.delete(11);
		});
	}
	
	@Test
	public void testDeleteRoleWithInvalidId() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.delete(0);
		});
		String expectedMessage = "Role Id is invalid";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteRoleWithNotExistRoleId() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.delete(5);
		});
		String expectedMessage = "Role Id is not exist in the table";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
