package in.fssa.leavepulse.dto;

public class EmployeeRoleDTO {
	
	int empRoleId;
	String employeeName;
	String managerName;
	String roleName;
	
	public int getEmpRoleId() {
		return empRoleId;
	}
	public void setEmpRoleId(int empRoleId) {
		this.empRoleId = empRoleId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return "EmployeeRoleDTO [empRoleId=" + empRoleId + ", employeeName=" + employeeName + ", managerName="
				+ managerName + ", roleName=" + roleName + "]";
	}

}
