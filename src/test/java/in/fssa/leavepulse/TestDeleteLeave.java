package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.LeaveService;

public class TestDeleteLeave {
	
	@Test
	public void testDeleteLeaveWithValidId() {
		LeaveService leaveService = new LeaveService();
		assertDoesNotThrow(() -> {
			leaveService.delete(4);
		});
	}
	
	@Test
	public void testDeleteLeaveWithInvalidId() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.delete(0);
		});
		String expectedMessage = "Leave Id is invalid";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteLeaveWithNotExistLeaveId() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.delete(5);
		});
		String expectedMessage = "Leave Id is not exist in the table";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
