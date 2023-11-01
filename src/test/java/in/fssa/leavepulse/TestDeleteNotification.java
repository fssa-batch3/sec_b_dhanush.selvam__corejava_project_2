package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.NotificationService;

public class TestDeleteNotification {
	
	@Test
	public void testDeleteNotification() {
		NotificationService notificationService = new NotificationService();
		int notificationId = 1;
		assertDoesNotThrow(() -> {
			notificationService.delete(notificationId);
		});
	}
	
	@Test
	public void testDeleteNotificationByInvalidNotificationId() {
		NotificationService notificationService = new NotificationService();
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.delete(0);
		});
		String expectedMessage = "Invalid Notification Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testDeleteNotificationByNotExistNotificationId() {
		NotificationService notificationService = new NotificationService();
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.delete(100);
		});
		String expectedMessage = "Notification Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
