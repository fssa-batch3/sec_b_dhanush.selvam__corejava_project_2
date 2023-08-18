package in.fssa.leavepulse.model;

public class Role implements Comparable<Role>{

	private int role_id;
	private String role_name;
	private boolean is_active;
	
	public Role() {
		
	}
	
	public Role(String role_name) {
		this.role_name = role_name;
	}
	
	public int getRoleId() {
		return role_id;
	}
	public void setRoleId(int role_id) {
		this.role_id = role_id;
	}
	public String getRoleName() {
		return role_name;
	}
	public void setRoleName(String role_name) {
		this.role_name = role_name;
	}
	
	public boolean getIsActive() {
		return is_active;
	}
	public void setIsActive(boolean is_active) {
		this.is_active = is_active;
	}
	
	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name + ", is_active=" + is_active + "]";
	}
	
	@Override
	public int compareTo(Role o) {
		if (this.getRoleId() == o.getRoleId())
			return 0;
		else {
			if (this.getRoleId() > o.getRoleId())
				return 1;
			else
				return -1;
		}
	}

}
