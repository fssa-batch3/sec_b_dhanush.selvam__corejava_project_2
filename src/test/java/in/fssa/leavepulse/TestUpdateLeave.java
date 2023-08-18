package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.service.LeaveService;

public class TestUpdateLeave {
	
	@Test
	public void testUpdateLeaveWithValidData() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave");
		assertDoesNotThrow(() -> {
			leaveService.update(4, leave);
		});
	}
	
	@Test
	public void testUpdateLeaveWithInvalidDataNull() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.update(4, null);
		});
		String expectedMessage = "Leave cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithLeaveNameNull() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave(null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.update(4, leave);
		});
		String expectedMessage = "Leave Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithLeaveNameEmpty() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("");
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.update(4, leave);
		});
		String expectedMessage = "Leave Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithNotExistLeaveId() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Casual Leave");
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.update(5, leave);
		});
		String expectedMessage = "Leave Id is not exist in the table";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateLeaveWithExistLeaveName() {
		LeaveService leaveService = new LeaveService();
		Leave leave = new Leave("Sick Leave");
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.update(4, leave);
		});
		String expectedMessage = "Leave Name already exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
