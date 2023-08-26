package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;  
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.EmployeeGenerator;
import in.fssa.leavepulse.model.Role;
import in.fssa.leavepulse.service.RoleService;

public class TestCreateRole {
	
	@Test
	public void testCreateRoleWithValidData() {
		RoleService roleService = new RoleService();
		Role role = new Role(new EmployeeGenerator().nameGenerator());
		assertDoesNotThrow(() -> {
			roleService.createRole(role);
		});
	}
	
	@Test
	public void testCreateRoleWithInvalidDataNull() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(null);
		});
		String expectedMessage = "Role cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateRoleWithRoleNameNull() {
		RoleService roleService = new RoleService();
		Role role = new Role(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(role);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRoleWithRoleNameEmpty() {
		RoleService roleService = new RoleService();
		Role role = new Role("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(role);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRoleWithExistRoleName() {
		RoleService roleService = new RoleService();
		Role role = new Role("CEO");
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.createRole(role);
		});
		String expectedMessage = "Role Name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
