package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Role;
import in.fssa.leavepulse.service.RoleService;

public class TestUpdateRole {
	
	@Test
	public void testUpdateRoleWithValidData() {
		RoleService roleService = new RoleService();
		Role role = new Role("Devops");
		assertDoesNotThrow(() -> {
			roleService.update(4,role);
		});
	}
	
	@Test
	public void testUpdateRoleWithInvalidDataNull() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(12,null);
		});
		String expectedMessage = "Role cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateRoleWithRoleNameNull() {
		RoleService roleService = new RoleService();
		Role role = new Role(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(12, role);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithRoleNameEmpty() {
		RoleService roleService = new RoleService();
		Role role = new Role("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(12, role);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithNotExistRoleId() {
		RoleService roleService = new RoleService();
		Role role = new Role("Team Lead");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(8, role);
		});
		String expectedMessage = "Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithExistRoleName () {
		RoleService roleService = new RoleService();
		Role role = new Role("CEO");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(4, role);
		});
		String expectedMessage = "Role Name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}