package in.fssa.leavepulse.model;

public class Role implements Comparable<Role>{

	private int roleId;
	private String roleName;
	
	public Role() {
		
	}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
	    return "{roleId:" + roleId + ", roleName:\"" + roleName +"\"}";
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
