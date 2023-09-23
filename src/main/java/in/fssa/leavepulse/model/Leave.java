package in.fssa.leavepulse.model;

public class Leave implements Comparable<Leave>{
	
	private int leave_id;
	private String leave_type;
	private boolean is_active;
	
	public Leave() {
		
	}
	
	public Leave(String leave_type) {
		this.leave_type = leave_type;
	}

	public int getLeaveId() {
		return leave_id;
	}
	public void setLeaveId(int leave_id) {
		this.leave_id = leave_id;
	}
	public String getLeaveType() {
		return leave_type;
	}
	public void setLeaveType(String leave_type) {
		this.leave_type = leave_type;
	}
	public boolean isIsActive() {
		return is_active;
	}
	public void setIsActive(boolean is_active) {
		this.is_active = is_active;
	}
	
	@Override
	public String toString() {
	    return "{leave_id:" + leave_id + ", leave_type:\"" + leave_type + "\", is_active:" + is_active + "}";
	}

	@Override
	public int compareTo(Leave o) {
		if (this.getLeaveId() == o.getLeaveId())
			return 0;
		else {
			if (this.getLeaveId() > o.getLeaveId())
				return 1;
			else
				return -1;
		}
	}
	
}
