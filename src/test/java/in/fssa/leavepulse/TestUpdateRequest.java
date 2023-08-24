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
		Request request = new Request(LeaveStatus.Accepted, 3, "Permission Granted");
		assertDoesNotThrow(() -> {
			requestService.update(requestId, request);
		});
	}
	
	@Test
	public void testUpdateRequestWithInvalidRequestId() {
		RequestService requestService = new RequestService();
		Request request = new Request(LeaveStatus.Accepted, 3, "Permission Granted");
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.update(0, request);
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
			requestService.update(20, request);
		});
		String expectedMessage = "Request Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRequestWithInvalidData() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.update(3, null);
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
			requestService.update(3, request);
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateRequestWithNotExistManagerId() {
		RequestService requestService = new RequestService();
		Request request = new Request(LeaveStatus.Accepted, 50, "Permission Granted");
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.update(2, request);
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
