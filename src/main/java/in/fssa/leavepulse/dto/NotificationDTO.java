package in.fssa.leavepulse.dto;

import java.sql.Timestamp;

public class NotificationDTO {
	
	private int notificationId;
	private String sender;
	private int receiver;
	private char message;
	private Timestamp createdAt;
	
	public int getNotificationId() {
		return notificationId;
	}
	
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public char getMessage() {
		return message;
	}

	public void setMessage(char message) {
		this.message = message;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp timestamp) {
		this.createdAt = timestamp;
	}
	
	@Override
	public String toString() {
	    return "{notificationId: " + notificationId + ", sender: \"" + sender + "\", message: \"" + message + "\", createdAt: \"" + createdAt + "\"}";
	}

}

