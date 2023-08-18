package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.service.EmployeeService;

public class TestUpdateEmployee {

	@Test
	public void testUpdateEmployeeWithValidData() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Thala","ajith@gmail.com",9876543210l,"Aa!12345","No.07/12, West Church Street, Boat Club - 600032");
		assertDoesNotThrow(() -> {
			employeeService.update(5, employee);
		});
	}

	@Test
	public void testUpdateEmployeeWithInvalidEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Thala","ajith@gmail.com",9876543210l,"Aa!12345","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(0, employee);
		});
		String expectedMessage = "Employee Id is invalid";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateEmployeeWithNotExistEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Thala", "ajith@gmail.com", 9876543210l, "Aa!12345","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(7, employee);
		});
		String expectedMessage = "Employee Id is not exist in the table";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithInvalidData() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, null);
		});
		String expectedMessage = "Employee cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithFirstNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee(null, "Thala", "ajith@gmail.com", 9876543210l, "Aa!12345","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "First Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithFirstNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("", "Thala", "ajith@gmail.com", 9876543210l, "Aa!12345","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "First Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithLastNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", null, "ajith@gmail.com", 9876543210l, "Aa!12345","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "Last Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithLastNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "", "ajith@gmail.com", 9876543210l, "Aa!12345","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "Last Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithPasswordNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Thala", "ajith@gmail.com", 9876543210l, null,"No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithPasswordEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Thala", "ajith@gmail.com", 9876543210l, "","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithInvalidPasswordFormat() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Thala", "ajith@gmail.com", 9876543210l, "A!123456","No.07/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "Password doesn't match the pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithAddressNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345",null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithAddressEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.update(5, employee);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}


}
