package in.fssa.leavepulse.model;

public class EmployeeRole implements Comparable<EmployeeRole> {
	
	private int empRoleId;
	private int employeeId;
	private int managerId;
	private int roleId;
	private boolean isActive;
	
	public EmployeeRole() {
		
	}
	
	public EmployeeRole(int employeeId, int managerId, int roleId) {
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.roleId = roleId;
	}
	
	public int getEmpRoleId() {
		return empRoleId;
	}
	public void setEmpRoleId(int empRoleId) {
		this.empRoleId = empRoleId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "EmployeeRole [empRoleId=" + empRoleId + ", employeeId=" + employeeId + ", managerId=" + managerId
				+ ", roleId=" + roleId + ", isActive=" + isActive + "]";
	}

	@Override
	public int compareTo(EmployeeRole o) {
		if (this.getEmpRoleId() == o.getEmpRoleId())
			return 0;
		else {
			if (this.getEmpRoleId() > o.getEmpRoleId())
				return 1;
			else
				return -1;
		}
	}

}
