package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.Generator;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.service.LeaveService;

public class TestCreateLeave {
	
	@Test
	public void testCreateLeaveWithValidData() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave(new Generator().nameGenerator(), 5);
		assertDoesNotThrow(() -> {
			leaveService.createLeave(leave);
		});
	}
	
	@Test
	public void testCreateLeaveWithInvalidDataNull() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.createLeave(null);
		});
		String expectedMessage = "Leave cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithLeaveTypeNull() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave(null, 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.createLeave(leave);
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithLeaveTypeEmpty() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.createLeave(leave);
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithInvalidLeaveDays() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave", 0);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.createLeave(leave);
		});
		String expectedMessage = "Leave Days cannot be less than 1";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithExistLeaveType() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.createLeave(leave);
		});
		String expectedMessage = "Leave Type already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
