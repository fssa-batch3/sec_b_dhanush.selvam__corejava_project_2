package in.fssa.leavepulse.model;

public class LeaveBalance implements Comparable<LeaveBalance> {

	private int leaveBalanceId;
	private int employeeId;
	private int leaveId;
	private int availableLeaveDays;

	public LeaveBalance() {

	}

	public LeaveBalance(int employeeId, int leaveId, int availableLeaveDays) {
		this.employeeId = employeeId;
		this.leaveId = leaveId;
		this.availableLeaveDays = availableLeaveDays;
	}

	public int getLeaveBalanceId() {
		return leaveBalanceId;
	}

	public void setLeaveBalanceId(int leaveBalanceId) {
		this.leaveBalanceId = leaveBalanceId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getAvailableLeaveDays() {
		return availableLeaveDays;
	}

	public void setAvailableLeaveDays(int availableLeaveDays) {
		this.availableLeaveDays = availableLeaveDays;
	}

	@Override
	public String toString() {
		return "{leaveBalanceId=" + leaveBalanceId + ", employeeId=" + employeeId + ", leaveId="
				+ leaveId + ", availableLeaveDays=" + availableLeaveDays + '}';
	}

	@Override
	public int compareTo(LeaveBalance o) {
		if (this.getLeaveBalanceId() == o.getLeaveBalanceId())
			return 0;
		else {
			if (this.getLeaveBalanceId() > o.getLeaveBalanceId())
				return 1;
			else
				return -1;
		}
	}

}
