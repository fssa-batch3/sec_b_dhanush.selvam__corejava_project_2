package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.dao.EmployeeDAO;
import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.EmployeeGenerator;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.service.EmployeeService;

public class TestUpdateEmployee {

	@Test
	public void testUpdateEmployeeWithValidData() {
		EmployeeService employeeService = new EmployeeService();
		EmployeeGenerator employeeGenerator = new EmployeeGenerator();
		int employeeId = new EmployeeDAO().getLastEmployeeId();
		Employee employee = new Employee(employeeGenerator.nameGenerator(), employeeGenerator.nameGenerator(),"ajith@gmail.com",8387393849l,"Aa!12345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
		assertDoesNotThrow(() -> {
			employeeService.updateEmployee(employeeId, employee);
		});
	}

	@Test
	public void testUpdateEmployeeWithInvalidEmployeeId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",8387393849l,"Aa!12345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
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
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",8387393849l,"Aa!12345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
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
		Employee employee = new Employee(null,"Kumar","ajith@gmail.com",8387393849l,"Aa!12345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
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
		Employee employee = new Employee("","Kumar","ajith@gmail.com",8387393849l,"Aa!12345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "First Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithLastNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith",null,"ajith@gmail.com",8387393849l,"Aa!12345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
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
		Employee employee = new Employee("Ajith","","ajith@gmail.com",8387393849l,"Aa!12345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Last Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithPasswordNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",8387393849l,null,"No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithPasswordEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",8387393849l,"","No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithInvalidPasswordFormat() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",8387393849l,"Aa6712345","No.23/10, 1st Avenue, Ashok Nagar - 600083");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Password doesn't match the pattern";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithAddressNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",8387393849l,"Aa!12345",null);
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testUpdateEmployeeWithAddressEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",8387393849l,"Aa!12345","");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.updateEmployee(4, employee);
		});
		String expectedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}


}
