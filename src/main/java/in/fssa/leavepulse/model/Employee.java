package in.fssa.leavepulse.model;

import java.time.LocalDate;

public class Employee implements Comparable<Employee> {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNo;
	private String password;
	private String address;
	private LocalDate joiningDate;

	public Employee() {

	}

	public Employee(String firstName, String lastName, String email, long phoneNo, String password, String address, LocalDate joiningDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.password = password;
		this.address = address;
		this.joiningDate = joiningDate;
	}
	
	public Employee(String firstName, String lastName, long phoneNo, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.address = address;
	}

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

	@Override
	public String toString() {
		return "{employeeId:" + employeeId + ", firstName:\"" + firstName + "\", lastName:\"" + lastName
				+ "\", email:\"" + email + "\", phoneNo:" + phoneNo + ", password:\"" + password + "\", address:\""
				+ address + "\", joiningDate:\"" + joiningDate + "\"}";
	}

	@Override
	public int compareTo(Employee o) {
		if (this.getEmployeeId() == o.getEmployeeId())
			return 0;
		else {
			if (this.getEmployeeId() > o.getEmployeeId())
				return 1;
			else
				return -1;
		}
	}

}
