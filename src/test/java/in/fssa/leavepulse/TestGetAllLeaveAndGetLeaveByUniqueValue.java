package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.LeaveService;

public class TestGetAllLeaveAndGetLeaveByUniqueValue {
	
	
	@Test
	public void testGetAllLeave() {
		LeaveService leaveService = new LeaveService();
		assertDoesNotThrow(() -> {
			System.out.println(leaveService.getAll());
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
			System.out.println(leaveService.findLeaveByLeaveId(0));
		});
		String expectedMessage = "Leave Id is invalid";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test 
	public void testGetLeaveByLeaveName() {
		LeaveService leaveService = new LeaveService();
		assertDoesNotThrow(() -> {
			System.out.println(leaveService.findLeaveByLeaveName("Personal Leave"));
		});
	}
	
	@Test
	public void testGetLeaveByInvalidLeaveNameNull() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(leaveService.findLeaveByLeaveName(null));
		});
		String expectedMessage = "Leave Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetLeaveByInvalidLeaveNameEmpty() {
		LeaveService leaveService = new LeaveService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(leaveService.findLeaveByLeaveName(""));
		});
		String expectedMessage = "Leave Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
