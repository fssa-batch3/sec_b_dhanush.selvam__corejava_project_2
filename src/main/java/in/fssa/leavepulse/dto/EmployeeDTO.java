package in.fssa.leavepulse.dto;

import java.time.LocalDate;

public class EmployeeDTO {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNo;
	private String password;
	private String address;
	private LocalDate joiningDate;
	private int managerId;
	private String managerEmail;
	private String roleName;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "{ \"employeeId\": " + employeeId + ", \"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName
				+ "\", \"email\": \"" + email + "\", \"phoneNo\": " + phoneNo + ", \"password\": \"" + password
				+ "\", \"address\": \"" + address + "\", \"joiningDate\": \"" + joiningDate + "\", \"managerId\": "
				+ managerId + ", \"managerEmail\": \"" + managerEmail + "\", \"roleName\": \"" + roleName + "\" }";
	}
	
}
