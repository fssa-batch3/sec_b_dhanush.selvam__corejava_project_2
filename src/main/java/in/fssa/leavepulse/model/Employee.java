package in.fssa.leavepulse.model;

import java.time.LocalDate;

public class Employee implements Comparable<Employee> {

	private int employee_id;

	private String first_name;
	private String last_name;
	private String email;
	private long phone_no;
	private String password;
	private String address;
	private LocalDate hire_date;
	private boolean isActive;

	public Employee() {

	}

	public Employee(String first_name, String last_name, String email, long phone_no, String password, String address) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_no = phone_no;
		this.password = password;
		this.address = address;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
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

	public LocalDate getHire_date() {
		return hire_date;
	}

	public void setHire_date(LocalDate hire_date) {
		this.hire_date = hire_date;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", phone_no=" + phone_no + ", password=" + password + ", address=" + address
				+ ", hire_date=" + hire_date + ", isActive=" + isActive + "]";
	}

	@Override
	public int compareTo(Employee o) {
		if (this.getEmployee_id() == o.getEmployee_id())
			return 0;
		else {
			if (this.getEmployee_id() > o.getEmployee_id())
				return 1;
			else
				return -1;
		}
	}

}
