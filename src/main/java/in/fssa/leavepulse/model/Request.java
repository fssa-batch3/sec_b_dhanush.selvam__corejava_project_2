package in.fssa.leavepulse.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Request implements Comparable<Request> {

	private int requestId;
	private int leaveId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reason;
	private int createdBy;
	private int modifiedBy;
	private int managerId;
	private Timestamp createdAt;
	private LeaveStatus leaveStatus;
	private String comments;

	public enum LeaveStatus {
		Pending, Accepted, Rejected, Cancelled
	}

	public Request() {

	}

	public Request(int leaveId, LocalDate startDate, LocalDate endDate, String reason, int createdBy, int managerId) {
		this.leaveId = leaveId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.createdBy = createdBy;
		this.managerId = managerId;
	}

	public Request(LeaveStatus leaveStatus, int modifiedBy, String comments) {
		this.leaveStatus = leaveStatus;
		this.modifiedBy = modifiedBy;
		this.comments = comments;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
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

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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

	@Override
	public String toString() {
		return "{ \"requestId\": " + requestId + ", \"leaveId\": " + leaveId + ", \"startDate\": \"" + startDate
				+ "\", \"endDate\": \"" + endDate + "\", \"reason\": \"" + reason + "\", \"createdBy\": " + createdBy
				+ "\"managerId\": " + managerId + ", \"createdAt\": \"" + createdAt + "\", \"leaveStatus\": \""
				+ leaveStatus + "\", \"comments\": \"" + comments + "\"}";
	}

	@Override
	public int compareTo(Request o) {
		if (this.getRequestId() == o.getRequestId())
			return 0;
		else {
			if (this.getRequestId() > o.getRequestId())
				return 1;
			else
				return -1;
		}
	}

}
