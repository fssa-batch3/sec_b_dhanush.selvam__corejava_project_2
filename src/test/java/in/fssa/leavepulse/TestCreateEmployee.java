package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.service.EmployeeService;

public class TestCreateEmployee {
	
	@Test
	public void testCreateEmployeeWithValidData() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		assertDoesNotThrow(() -> {
			employeeService.create(employee);
		});
	}
	
	@Test
	public void testCreateEmployeeWithInvalidData() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(null);
		});
		String expectedMessage = "Employee cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithFirstNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee(null,"Kumar","ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "First Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithFirstNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "First Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithLastNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith",null,"ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Last Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithLastNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","","ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Last Name cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithEmailNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar",null,9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Email cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithEmailEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Email cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithInvalidEmailFormat() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail,com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Invalid Email Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithInvalidPhoneNo() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",987654321l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Invalid Phone Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithPasswordNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,null,"No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithPasswordEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Password cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithInvalidPasswordFormat() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa123456","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Password doesn't match the pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithAddressNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345",null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithAddressEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee);
		});
		String expectedMessage = "Address cannot be Null or Empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
