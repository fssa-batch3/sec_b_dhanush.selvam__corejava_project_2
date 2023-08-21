package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.RequestService;

public class TestDeleteRequest {

	@Test
	public void testDeleteRequestWithValidRequestId() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			requestService.delete(3);
		});
	}
	
	@Test
	public void testDeleteRequestWithInvalidRequestId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.delete(0);
		});
		String expectedMessage = "Invalid Request Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
	@Test
	public void testDeleteRequestWithNotExistRequestId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.delete(6);
		});
		String expectedMessage = "Request Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));

	}
	
}
