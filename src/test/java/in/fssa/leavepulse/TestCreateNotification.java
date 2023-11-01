package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Notification;
import in.fssa.leavepulse.service.NotificationService;

public class TestCreateNotification {
	
	@Test
	public void testCreateNotification() {
		NotificationService notificationService = new NotificationService();
		Notification notification = new Notification(5, 2, 'N');
		assertDoesNotThrow(() -> {
			notificationService.create(notification);
		});
	}
	
	@Test
	public void testCreateNotificationByInvalidDataNull() {
		NotificationService notificationService = new NotificationService();
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.create(null);
		});
		String expectedMessage = "Notification cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateNotificationByInvalidSenderId() {
		NotificationService notificationService = new NotificationService();
		Notification notification = new Notification(-5, 2, 'N');
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.create(notification);
		});
		String expectedMessage = "Invalid Sender Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateNotificationByNotExistSenderId() {
		NotificationService notificationService = new NotificationService();
		Notification notification = new Notification(500, 2, 'N');
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.create(notification);
		});
		String expectedMessage = "Sender Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateNotificationByInvalidReceiverId() {
		NotificationService notificationService = new NotificationService();
		Notification notification = new Notification(5, -2, 'N');
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.create(notification);
		});
		String expectedMessage = "Invalid Receiver Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateNotificationByNotExistReceiverId() {
		NotificationService notificationService = new NotificationService();
		Notification notification = new Notification(5, 200, 'N');
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.create(notification);
		});
		String expectedMessage = "Receiver Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateNotificationByInvalidMessage() {
		NotificationService notificationService = new NotificationService();
		Notification notification = new Notification(5, 2, '@');
		Exception exception = assertThrows(ValidationException.class, ()-> {
			notificationService.create(notification);
		});
		String expectedMessage = "Invalid Message";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
