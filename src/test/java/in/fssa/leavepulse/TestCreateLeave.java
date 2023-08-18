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
		Leave leave = new Leave("Casual Leave");
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
	public void testCreateLeaveWithLeaveNameNull() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.create(leave);
		});
		String expectedMessage = "Leave Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithLeaveNameEmpty() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.create(leave);
		});
		String expectedMessage = "Leave Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateLeaveWithExistLeaveName() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave");
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.create(leave);
		});
		String expectedMessage = "Leave Name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	

}
