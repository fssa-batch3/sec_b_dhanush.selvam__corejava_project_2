package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.Generator;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.service.EmployeeService;

public class TestUpdateEmployee {

	@Test
	public void testUpdateEmployeeWithValidData() {
		EmployeeService employeeService = new EmployeeService();
		Generator employeeGenerator = new Generator();
		int employeeId = new EmployeeDAO().getLastEmployeeId();
		Employee employee = new Employee(employeeGenerator.nameGenerator(), employeeGenerator.nameGenerator(), employeeGenerator.numberGenenrator(), "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		assertDoesNotThrow(() -> {
			employeeService.updateEmployee(employeeId, employee);
		});
	}

	@Test
	public void testUpdateEmployeeWithInvalidEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Kumar", 8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(0, employee);
		});
		String expectedMessage = "Invalid Employee Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateEmployeeWithNotExistEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Kumar", 8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(500, employee);
		});
		String expectedMessage = "Employee Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithInvalidData() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, null);
		});
		String expectedMessage = "Employee cannot be null";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithFirstNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee(null, "Kumar" ,8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "First Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithFirstNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("", "Kumar" ,8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "First Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithInvalidFirstName() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("kdfj;^&87", "Kumar", 8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "First Name must contain only alphabets with minimum 3 letters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithLastNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", null, 8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Last Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithLastNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "", 8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Last Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithInvalidLastName() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "jfdlj&*(78", 8387393849l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Last Name must contain only alphabets with minimum 3 letters and spaces are allowed";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithAddressNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Kumar", 8387393849l, null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithInvalidPhoneNo() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Kumar", 8387393l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Invalid Phone Number";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithAlreadyExistPhoneNo() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Kumar", 9025214260l, "No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Phone Number alreay exist";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithAddressEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Kumar", 8387393849l, "");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testUpdateEmployeeWithInvalidAddress() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith", "Kumar", 8387393849l, "dlj;!@#$%^ld@#$%^&*(83");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Invalid Address Pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

}
