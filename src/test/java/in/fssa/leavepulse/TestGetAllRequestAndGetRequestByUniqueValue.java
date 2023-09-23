package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.service.RequestService;

public class TestGetAllRequestAndGetRequestByUniqueValue {
	
	@Test
	public void testGetAllRequest() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			System.out.println(requestService.getAllRequest());
		});
	}
	
	@Test
	public void testFindRequestByRequestId() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			System.out.println(requestService.findRequestByRequestId(1));
		});
	}

	@Test
	public void testFindRequestByInvalidRequestId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(requestService.findRequestByRequestId(0));
		});
		String expectedMessage = "Invalid Request Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAllRequestByLeaveId() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			System.out.println(requestService.findAllRequestByLeaveId(1));
		});
	}
	
	@Test
	public void testFindAllRequestByInvalidLeaveId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(requestService.findAllRequestByLeaveId(0));
		});
		String expectedMessage = "Invalid Leave Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	@Test
	public void testFindAllRequestByNotExistLeaveId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(requestService.findAllRequestByLeaveId(500));
		});
		String expectedMessage = "Leave Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindAllRequestByManagerId() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			System.out.println(requestService.findAllRequestByManagerId(2));
		});
	}
	
	@Test
	public void testGetRequestByInvalidManagerId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(requestService.findAllRequestByManagerId(0));
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetRequestByNotExistManagerId() {
		RequestService requestService = new RequestService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			System.out.println(requestService.findAllRequestByManagerId(500));
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testGetAllRequestWithEmployee() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			System.out.println(requestService.getAllRequestWithEmployee());
		});
	}
	
	@Test
	public void testGetAllRequestWithEmployeeByManagerId() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			System.out.println(requestService.getAllRequestWithEmployeeByManagerId(2));
		});
	}
	
	@Test
	public void testGetAllRequestWithEmployeeByEmployeeId() {
		RequestService requestService = new RequestService();
		assertDoesNotThrow(() -> {
			System.out.println(requestService.getAllRequestWithEmployeeByEmployeeId(3));
		});
	}
	
}
