package in.fssa.leavepulse.dto;

public class LeaveBalanceDTO {

	private String leaveType;
	private int availableLeaveDays;

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getAvailableLeaveDays() {
		return availableLeaveDays;
	}

	public void setAvailableLeaveDays(int availableLeaveDays) {
		this.availableLeaveDays = availableLeaveDays;
	}

	@Override
	public String toString() {
		return "{leaveType:\"" + leaveType + "\", availableLeaveDays:" + availableLeaveDays + "}";
	}

}
