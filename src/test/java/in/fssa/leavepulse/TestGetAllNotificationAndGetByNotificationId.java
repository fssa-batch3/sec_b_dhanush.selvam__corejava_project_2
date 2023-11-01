package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.service.NotificationService;

public class TestGetAllNotificationAndGetByNotificationId {
	
//	@Test
//	public void testGetNotificationByNotificationId() {
//		NotificationService notificationService = new NotificationService();
//		assertDoesNotThrow(() -> {
//			System.out.println(notificationService.checkNotificationIdIs(2));
//		});
//	}
	
	@Test
	public void testfindAllNotificationByEmployeeId() {
		NotificationService notificationService = new NotificationService();
		assertDoesNotThrow(() -> {
			System.out.println(notificationService.findAllNotificationByEmployeeId(2));
		});
	}

}
