package in.fssa.leavepulse.model;

public class Leave implements Comparable<Leave> {

	private int leaveId;
	private String leaveType;
	private int leaveDays;

	public Leave() {

	}

	public Leave(String leaveType, int leaveDays) {
		this.leaveType = leaveType;
		this.leaveDays = leaveDays;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}

	@Override
	public String toString() {
		return "{leaveId:" + leaveId + ", leaveType:\"" + leaveType + "\", leaveDays:" + leaveDays + "}";
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
