package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.Generator;
import in.fssa.leavepulse.service.RoleService;

public class TestCreateRole {
	
	@Test
	public void testCreateRoleWithValidData() {
		RoleService roleService = new RoleService();
		String roleName = new Generator().nameGenerator();
		assertDoesNotThrow(() -> {
			roleService.createRole(roleName);
		});
	}

	@Test
	public void testCreateRoleWithRoleNameNull() {
		RoleService roleService = new RoleService();
		String roleName = null;
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(roleName);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRoleWithRoleNameEmpty() {
		RoleService roleService = new RoleService();
		String roleName = "";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(roleName);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRoleWithInvalidRoleName() {
		RoleService roleService = new RoleService();
		String roleName = "kj89(&*";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(roleName);
		});
		String expectedMessage = "Role Name must contain only alphabets with minimum 3 letters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRoleWithExistRoleName() {
		RoleService roleService = new RoleService();
		String roleName = "CEO";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(roleName);
		});
		String expectedMessage = "Role Name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
