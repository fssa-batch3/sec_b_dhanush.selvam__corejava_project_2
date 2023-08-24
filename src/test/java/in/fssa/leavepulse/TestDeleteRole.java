package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.RoleDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.RoleService;

public class TestDeleteRole {
	
	@Test
	public void testDeleteRoleWithValidRoleId() {
		RoleService roleService = new RoleService();
		assertDoesNotThrow(() -> {
			roleService.delete(new RoleDAO().getLastRoleId());
		});
	}
	
	@Test
	public void testDeleteRoleWithInvalidRoleId() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.delete(0);
		});
		String expectedMessage = "Invalid Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteRoleWithNotExistRoleId() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.delete(20);
		});
		String expectedMessage = "Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
