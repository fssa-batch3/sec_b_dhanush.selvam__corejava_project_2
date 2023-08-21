package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Request;
import in.fssa.leavepulse.service.RequestService;

public class TestCreateRequest {
	
	@Test
	public void testCreateRequestWithValidData() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 8, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 3);
		assertDoesNotThrow(() -> {
			requestService.create(request);
		});
	}
	
	@Test
	public void testCreateRequestWithInvalidData() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.create(null);
		});
		String expectedMessage = "Request cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateRequestWithInvalidLeaveId() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 8, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(0, startDate, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.create(request);
		});
		String expectedMessage = "Invalid Leave Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithNotExistInvalidId() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 8, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(8, startDate, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.create(request);
		});
		String expectedMessage = "Leave Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidEmployeeId() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 8, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 0, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.create(request);
		});
		String expectedMessage = "Invalid Employee Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithNotExistEmployeeId() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 8, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 8, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.create(request);
		});
		String expectedMessage = "Employee Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidManagerId() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 8, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 0);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.create(request);
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithNotExistManagerId() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 8, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 8);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.create(request);
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
