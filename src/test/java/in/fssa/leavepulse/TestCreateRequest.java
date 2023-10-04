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
		LocalDate startDate = LocalDate.of(2023, 10, 01);
		LocalDate endDate = LocalDate.of(2023, 10, 03);
		RequestService requestService = new RequestService();
		Request request = new Request(4, startDate, endDate, "General check-up", 13, 4);
		assertDoesNotThrow(() -> {
			requestService.createRequest(request);
		});
	}
	
	@Test
	public void testCreateRequestWithInvalidData() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(null);
		});
		String expectedMessage = "Request cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateRequestWithInvalidLeaveId() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		LocalDate endDate = LocalDate.of(2023, 10, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(0, startDate, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Invalid Leave Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithNotExistLeaveId() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		LocalDate endDate = LocalDate.of(2023, 10, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(500, startDate, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Leave Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithStartDateNull() {
		LocalDate endDate = LocalDate.of(2023, 9, 25);
		RequestService requestService = new RequestService();
		Request request = new Request(1, null, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Start Date should not be empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidStartDate() {
		LocalDate startDate = LocalDate.of(2023, 8, 22);
		LocalDate endDate = LocalDate.of(2023, 9, 25);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Start Date should be on or after today";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithEndDateNull() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, null, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "End Date should not be empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidEndDate() {
		LocalDate startDate = LocalDate.of(2023, 10, 25);
		LocalDate endDate = LocalDate.of(2023, 9, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "End Date should be on or after Start Date";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidReason() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		LocalDate endDate = LocalDate.of(2023, 10, 25);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "$%^&%^&khd", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Only alphanumeric characters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidEmployeeId() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		LocalDate endDate = LocalDate.of(2023, 10, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 0, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Invalid Employee Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithNotExistEmployeeId() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		LocalDate endDate = LocalDate.of(2023, 10, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 500, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Employee Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithInvalidManagerId() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		LocalDate endDate = LocalDate.of(2023, 10, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 0);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithNotExistManagerId() {
		LocalDate startDate = LocalDate.of(2023, 10, 22);
		LocalDate endDate = LocalDate.of(2023, 10, 23);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 500);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateRequestWithAlreadyExistLeaveDates() {
		LocalDate startDate = LocalDate.of(2023, 9, 24);
		LocalDate endDate = LocalDate.of(2023, 9, 26);
		RequestService requestService = new RequestService();
		Request request = new Request(1, startDate, endDate, "Fever", 4, 3);
		Exception exception = assertThrows(ValidationException.class, () -> {
			requestService.createRequest(request);
		});
		String expectedMessage = "You have been already applied a leave request in these days";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
}
