package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.RoleService;

public class TestGetAllRoleAndGetRoleByUniqueValue {
		
	@Test
	public void testGetAllRoles() {
		RoleService roleService = new RoleService();
		assertDoesNotThrow(() -> {
			System.out.println(roleService.getAllRole());
		});
	}
	
	@Test 
	public void testGetRoleByRoleId() {
		RoleService roleService = new RoleService();
		assertDoesNotThrow(() -> {
			System.out.println(roleService.findRoleByRoleId(1));
		});
	}
	
	@Test
	public void testGetRoleByInvalidRoleId() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(roleService.findRoleByRoleId(0));
		});
		String expectedMessage = "Invalid Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test 
	public void testGetRoleByRoleName() {
		RoleService roleService = new RoleService();
		assertDoesNotThrow(() -> {
			System.out.println(roleService.findRoleByRoleName("Developer"));
		});
	}
	
	@Test
	public void testGetRoleByInvalidRoleNameNull() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(roleService.findRoleByRoleName(null));
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetRoleByInvalidRoleNameEmpty() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(roleService.findRoleByRoleName(""));
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
