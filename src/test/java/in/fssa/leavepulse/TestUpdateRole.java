package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.RoleDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.Generator;
import in.fssa.leavepulse.service.RoleService;

public class TestUpdateRole {
	
	@Test
	public void testUpdateRoleWithValidData() {
		RoleService roleService = new RoleService();
		int roleId = new RoleDAO().getLastRoleId();
		String roleName = new Generator().nameGenerator();
		assertDoesNotThrow(() -> {
			roleService.updateRole(roleId,roleName);
		});
	}
	
	@Test
	public void testUpdateLeaveWithInvalidRoleId() {
		RoleService roleService = new RoleService();
		String roleName = "CEO";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.updateRole(0, roleName);
		});
		String expectedMessage = "Invalid Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithRoleNameNull() {
		RoleService roleService = new RoleService();
		String roleName = null;
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.updateRole(2, roleName);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithRoleNameEmpty() {
		RoleService roleService = new RoleService();
		String roleName = "";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.updateRole(2, roleName);
		});
		String expectedMessage = "Role Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithInvalidRoleName() {
		RoleService roleService = new RoleService();
		String roleName = "hdk*(*78";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.updateRole(2, roleName);
		});
		String expectedMessage = "Role Name must contain only alphabets with minimum 3 letters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithNotExistRoleId() {
		RoleService roleService = new RoleService();
		String roleName = "Team Lead";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.updateRole(500, roleName);
		});
		String expectedMessage = "Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRoleWithExistRoleName () {
		RoleService roleService = new RoleService();
		String roleName = "CEO";
		Exception exception = assertThrows(ValidationException.class, () -> {
			roleService.updateRole(2, roleName);
		});
		String expectedMessage = "Role Name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
