package in.fssa.leavepulse;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow; 
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.leavepulse.exception.ValidationException;
import in.fssa.leavepulse.generator.EmployeeGenerator;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.service.EmployeeService;

public class TestCreateEmployee {
	
	@Test
	public void testCreateEmployeeWithValidData() {
		EmployeeService employeeService = new EmployeeService();
		EmployeeGenerator employeeGenerator = new EmployeeGenerator();
		Employee employee = new Employee(employeeGenerator.nameGenerator(), employeeGenerator.nameGenerator(), employeeGenerator.emailGenerator(), employeeGenerator.numberGenenrator(), "Aa!12345", "No.10/25, East Church Street, Boat Club - 600032");
		assertDoesNotThrow(() -> {
			employeeService.create(employee,3,4);
		});
	}
	
	@Test
	public void testCreateEmployeeWithInvalidDataNull() {
		EmployeeService employeeService = new EmployeeService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(null,3,4);
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
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "First Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithFirstNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "First Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithLastNameNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith",null,"ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Last Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithLastNameEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","","ajith@gmail.com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Last Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithEmailNull() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar",null,9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithEmailEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithInvalidEmailFormat() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail,com",9876543210l,"Aa!12345","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
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
			employeeService.create(employee,3,4);
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
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithPasswordEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithInvalidPasswordFormat() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa123456","No.3/12, West Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
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
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithAddressEmpty() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,3,4);
		});
		String expectedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithInvalidManagerId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","No.10/25, East Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,0,4);
		});
		String expectedMessage = "Invalid Manager Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testCreateEmployeeWithNotExistManagerId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith1@gmail.com",9876543211l,"Aa!12345","No.10/25, East Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,20,4);
		});
		String expectedMessage = "Manager Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateEmployeeWithInvalidRoleId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith@gmail.com",9876543210l,"Aa!12345","No.10/25, East Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,2,0);
		});
		String expectedMessage = "Invalid Role Id";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateEmployeeWithNotExistRoleId() {
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Ajith","Kumar","ajith1@gmail.com",9876543211l,"Aa!12345","No.10/25, East Church Street, Boat Club - 600032");
		Exception exception = assertThrows(ValidationException.class, () -> {
			employeeService.create(employee,2,20);
		});
		String expectedMessage = "Role Id not found";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
