package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.EmployeeRoleService;

public class TestGetAllEmpRoleAndGetByEmpRoleByUniqueValue {

	@Test
	public void testGetAllEmpRole() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		assertDoesNotThrow(() -> {
			System.out.println(empRoleService.getAllEmpRole());
		});
	}
	
	@Test
	public void testGetEmpRoleByEmpRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		assertDoesNotThrow(() -> {
			System.out.println(empRoleService.findEmpRoleByEmpRoleId(1));
		});
	}
	
	@Test
	public void testGetEmpRoleByInvalidEmpRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception  = assertThrows(ValidationException.class, () -> {
			System.out.println(empRoleService.findEmpRoleByEmpRoleId(0));
		});
		String expectedMessage = "Invalid Employee-Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetEmpRoleByEmployeeId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		assertDoesNotThrow(() -> {
			System.out.println(empRoleService.findEmpRoleByEmployeeId(1));
		});
	}
	
	@Test
	public void testGetEmpRoleByInvalidEmployeeId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception  = assertThrows(ValidationException.class, () -> {
			System.out.println(empRoleService.findEmpRoleByEmployeeId(0));
		});
		String expectedMessage = "Invalid Employee Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetEmpRoleByNotExistEmployeeId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception  = assertThrows(ValidationException.class, () -> {
			System.out.println(empRoleService.findEmpRoleByEmployeeId(500));
		});
		String expectedMessage = "Employee Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAllEmpRoleByManagerId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		assertDoesNotThrow(() -> {
			System.out.println(empRoleService.findAllEmpRoleByManagerId(1));
		});
	}
	
	@Test
	public void testFindAllEmpRoleByInvalidManagerId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception  = assertThrows(ValidationException.class, () -> {
			System.out.println(empRoleService.findAllEmpRoleByManagerId(0));
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAllEmpRoleByNotExistManagerId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception  = assertThrows(ValidationException.class, () -> {
			System.out.println(empRoleService.findAllEmpRoleByManagerId(500));
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAllEmpRoleByRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		assertDoesNotThrow(() -> {
			System.out.println(empRoleService.findAllEmpRoleByRoleId(2));
		});
	}
	
	@Test
	public void testFindAllEmpRoleByInvalidRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception  = assertThrows(ValidationException.class, () -> {
			System.out.println(empRoleService.findAllEmpRoleByRoleId(0));
		});
		String expectedMessage = "Invalid Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAllEmpRoleByNotExistRoleId() {
		EmployeeRoleService empRoleService = new EmployeeRoleService();
		Exception exception  = assertThrows(ValidationException.class, () -> {
			System.out.println(empRoleService.findAllEmpRoleByRoleId(500));
		});
		String expectedMessage = "Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
