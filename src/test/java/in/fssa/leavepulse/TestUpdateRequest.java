package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.RequestDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Request;
import in.fssa.leavepulse.model.Request.LeaveStatus;
import in.fssa.leavepulse.service.RequestService;

public class TestUpdateRequest {

	@Test
	public void testUpdateRequestWithValidData() {
		RequestService requestService = new RequestService();
		int requestId = new RequestDAO().getLastRequestId();
		Request request = new Request(LeaveStatus.Accepted, 1, "Permission Granted");
		assertDoesNotThrow(() -> {
			requestService.updateRequest(requestId, request);
		});
	}
	
	@Test
	public void testUpdateRequestWithInvalidRequestId() {
		RequestService requestService = new RequestService();
		Request request = new Request(LeaveStatus.Accepted, 3, "Permission Granted");
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.updateRequest(0, request);
		});
		String expectedMessage = "Invalid Request Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRequestWithNotExistRequestId() {
		RequestService requestService = new RequestService();
		Request request = new Request(LeaveStatus.Accepted, 3, "Permission Granted");
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.updateRequest(500, request);
		});
		String expectedMessage = "Request Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRequestWithInvalidData() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.updateRequest(3, null);
		});
		String expectedMessage = "Request cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRequestWithInvalidManagerId() {
		RequestService requestService = new RequestService();
		Request request = new Request(LeaveStatus.Accepted, 0, "Permission Granted");
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.updateRequest(3, request);
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRequestWithNotExistManagerId() {
		RequestService requestService = new RequestService();
		Request request = new Request(LeaveStatus.Accepted, 500, "Permission Granted");
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.updateRequest(2, request);
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidComment() {
		RequestService requestService = new RequestService();
		Request request = new Request(LeaveStatus.Accepted, 1, "fg@#z45)(*");
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.updateRequest(2, request);
		});
		String expectedMessage = "Only alphanumeric characters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCancelRequestWithValidData() {
		RequestService requestService = new RequestService();
		int requestId = new RequestDAO().getLastRequestId();
		assertDoesNotThrow(() -> {
			requestService.cancelRequest(requestId);
		});
	}
	
	@Test
	public void testCancelRequestWithInvalidRequestId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.deleteRequest(0);
		});
		String expectedMessage = "Invalid Request Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testCancelRequestWithNotExistRequestId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.deleteRequest(500);
		});
		String expectedMessage = "Request Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
}
