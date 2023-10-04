package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.LeaveDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.Generator;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.service.LeaveService;

public class TestUpdateLeave {
	
	@Test
	public void testUpdateLeaveWithValidData() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave(new Generator().nameGenerator(), 5);
		int leaveId = new LeaveDAO().getLastLeaveId();
		assertDoesNotThrow(() -> {
			leaveService.updateLeave(leaveId, leave);
		});
	}
	
	@Test
	public void testUpdateLeaveWithInvalidLeaveId() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(0, leave);
		});
		String expectedMessage = "Invalid Leave Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithInvalidDataNull() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(4, null);
		});
		String expectedMessage = "Leave cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithLeaveTypeNull() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave(null, 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(4, leave);
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithLeaveTypeEmpty() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(4, leave);
		});
		String expectedMessage = "Leave Type cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithInvalidLeaveType() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("fkd78^&*(", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(4, leave);
		});
		String expectedMessage = "Leave Type must contain only alphabets with minimum 3 letters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithInvalidLeaveDays() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave", 0);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(5, leave);
		});
		String expectedMessage = "Leave Days cannot be less than 1";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithNotExistLeaveId() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(500, leave);
		});
		String expectedMessage = "Leave Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithExistLeaveType() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Sick Leave", 5);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.updateLeave(2, leave);
		});
		String expectedMessage = "Leave Type already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
