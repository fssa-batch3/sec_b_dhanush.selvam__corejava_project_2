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
	public void testGetRoleByRoleNameNull() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(roleService.findRoleByRoleName(null));
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetRoleByRoleNameEmpty() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(roleService.findRoleByRoleName(""));
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetRoleByInvalidRoleName() {
		RoleService roleService = new RoleService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(roleService.findRoleByRoleName("fhh*(98"));
		});
		String expectedMessage = "Role Name must contain only alphabets with minimum 3 letters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetTableLastRoleId() {
		RoleService roleService = new RoleService();
		assertDoesNotThrow(() -> {
			System.out.println(roleService.getTableLastRoleId());
		});
	}

}
