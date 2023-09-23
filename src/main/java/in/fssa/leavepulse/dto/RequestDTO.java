package in.fssa.leavepulse.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

public class RequestDTO {
	
	private int requestId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reason;
	private Timestamp createdAt;
	private LeaveStatus leaveStatus;
	private String comments;
	private String employeeName;
	private String employeeEmail;
	private String leaveType;
	private boolean cancelled_leave;
	
	public enum LeaveStatus {
		Pending,
		Accepted,
		Rejected
	}
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
	public boolean getCancelledLeave() {
		return cancelled_leave;
	}
	
	public void setCancelledLeave(boolean cancelled_leave) {
		this.cancelled_leave = cancelled_leave;
	}
	
	@Override
	public String toString() {
	    return "{requestId:" + requestId + ", startDate:\"" + startDate + "\", endDate:\"" + endDate
	            + "\", reason:\"" + reason + "\", createdAt:\"" + createdAt + "\", leaveStatus:\"" + leaveStatus
	            + "\", comments:\"" + comments + "\", employeeName:\"" + employeeName + "\", employeeEmail:\"" + employeeEmail
	            + "\", leaveType:\"" + leaveType + "\", cancelledLeave:" + cancelled_leave + "}";
	}


}
