package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.service.LeaveService;

public class TestCreateLeave {
	
	@Test
	public void testCreateLeaveWithValidData() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Maternity Leave");
		assertDoesNotThrow(() -> {
			leaveService.create(leave);
		});
	}
	
	@Test
	public void testCreateLeaveWithInvalidDataNull() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.create(null);
		});
		String expectedMessage = "Leave cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithLeaveTypeNull() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.create(leave);
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithLeaveTypeEmpty() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.create(leave);
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithExistLeaveType() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave");
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.create(leave);
		});
		String expectedMessage = "Leave Type already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
