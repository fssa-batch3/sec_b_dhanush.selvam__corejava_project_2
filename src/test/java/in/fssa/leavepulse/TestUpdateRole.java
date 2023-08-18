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
			roleService.update(11,role);
		});
	}
	
	@Test
	public void testUpdateRoleWithInvalidDataNull() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(11,null);
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
			roleService.update(11, role);
		});
		String expectedMessage = "Role Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithRoleNameEmpty() {
		RoleService roleService = new RoleService();
		Role role = new Role("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(11, role);
		});
		String expectedMessage = "Role Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithNotExistRoleId() {
		RoleService roleService = new RoleService();
		Role role = new Role("Team Lead");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(5, role);
		});
		String expectedMessage = "Role Id is not exist in the table";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithExistRoleName () {
		RoleService roleService = new RoleService();
		Role role = new Role("Developer");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.update(11, role);
		});
		String expectedMessage = "Role Name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
