package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.service.LeaveBalanceService;

public class TestGetAllLeaveBalAndGetLeaveBalByUnique {
	
	@Test
	public void testRemainingLeaveCountOfALeaveType() {
		LeaveBalanceService leaveBalService = new LeaveBalanceService();
		assertDoesNotThrow(() -> {
			System.out.println(leaveBalService.remainingLeaveCountOfALeaveType(5, 4));
		});
	}
	
	@Test
	public void testfindAllAvailableLeavesByEmployeeId() {
		LeaveBalanceService leaveBalService = new LeaveBalanceService();
		assertDoesNotThrow(() -> {
			System.out.println(leaveBalService.findAllAvailableLeavesByEmployeeId(5));
		});
	}

}
