package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.service.LeaveBalanceService;

public class TestUpdateLeaveBalance {

	@Test
	public void testUpdateLeaveDaysWithValidData() {
		LeaveBalanceService leaveBalService = new LeaveBalanceService();
		assertDoesNotThrow(() -> {
			leaveBalService.updateLeaveBalance("update", 2, 1, 5);
		});
	}
	
}
