package in.fssa.leavepulse.dto;

//import java.sql.Timestamp;
import java.time.LocalDate;

public class EmployeeDTO {

	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private long phone_no;
	private String password;
	private String address;
//	private Timestamp created_at;
	private LocalDate hire_date;
	private int manager_id;
	private String manager_email;
	private String role_name;

	public int getEmployeeId() {
		return employee_id;
	}

	public void setEmployeeId(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNo() {
		return phone_no;
	}

	public void setPhoneNo(long phone_no) {
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
	
	public LocalDate getHireDate() {
		return hire_date;
	}

	public void setHireDate(LocalDate hire_date) {
		this.hire_date = hire_date;
	}

//	public Timestamp getCreatedAt() {
//		return created_at;
//	}
//
//	public void setCreatedAt(Timestamp created_at) {
//		this.created_at = created_at;
//	}
	
	public int getManagerId() {
		return manager_id;
	}
	public void setManagerId(int manager_id) {
		this.manager_id = manager_id;
	}
	public String getManagerEmail() {
		return manager_email;
	}
	public void setManagerEmail(String manager_email) {
		this.manager_email = manager_email;
	}
	public String getRoleName() {
		return role_name;
	}

	public void setRoleName(String role_name) {
		this.role_name = role_name;
	}

	@Override
	public String toString() {
	    return "{employee_id:" + employee_id + ", first_name:\"" + first_name + "\", last_name:\"" + last_name
	            + "\", email:\"" + email + "\", phone_no:" + phone_no + ", password:\"" + password + "\", address:\"" + address
	            + "\", hire_date:\"" + hire_date + "\" , manager_id:" + manager_id + ", manager_email:\"" + manager_email + "\", roleName:\"" + role_name + "\"}";
	}


}
