package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.service.LeaveService;

public class TestGetAllLeaveAndGetLeaveByUniqueValue {
	
	@Test
	public void testGetAllLeave() {
		LeaveService leaveService = new LeaveService();
		assertDoesNotThrow(() -> {
			System.out.println(leaveService.getAllLeave());
		});
	}
	
	@Test 
	public void testGetLeaveByLeaveId() {
		LeaveService leaveService = new LeaveService();
		assertDoesNotThrow(() -> {
			System.out.println(leaveService.findLeaveByLeaveId(1));
		});
	}
	
	@Test
	public void testGetLeaveByInvalidLeaveId() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.findLeaveByLeaveId(0);
		});
		String expectedMessage = "Invalid Leave Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test 
	public void testGetLeaveByLeaveType() {
		LeaveService leaveService = new LeaveService();
		assertDoesNotThrow(() -> {
			System.out.println(leaveService.findLeaveByLeaveName("Sick Leave"));
		});
	}
	
	@Test
	public void testGetLeaveByLeaveTypeNull() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.findLeaveByLeaveName(null);
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetLeaveByLeaveTypeEmpty() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.findLeaveByLeaveName("");
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetLeaveByInvalidLeaveType() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("fkd78^&*(", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(4, leave);
		});
		String expectedMessage = "Leave Type must contain only alphabets with minimum 3 letters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
