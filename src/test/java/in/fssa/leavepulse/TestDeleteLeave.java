package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.LeaveDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.LeaveService;

public class TestDeleteLeave {
	
	@Test
	public void testDeleteLeaveWithValidLeaveId() {
		LeaveService leaveService = new LeaveService();
		int leaveId = new LeaveDAO().getLastLeaveId();
		assertDoesNotThrow(() -> {
			leaveService.delete(leaveId);
		});
	}
	
	@Test
	public void testDeleteLeaveWithInvalidLeaveId() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.delete(0);
		});
		String expectedMessage = "Invalid Leave Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteLeaveWithNotExistLeaveId() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			leaveService.delete(500);
		});
		String expectedMessage = "Leave Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
