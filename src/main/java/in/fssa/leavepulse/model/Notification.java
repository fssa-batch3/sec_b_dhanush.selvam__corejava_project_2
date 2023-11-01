package in.fssa.leavepulse.model; 

import java.sql.Timestamp;

public class Notification implements Comparable<Notification> {
	
	private int notificationId;
	private int sender;
	private int receiver;
	private char message;
	private Timestamp createdAt;
	
	public Notification(int sender, int receiver, char message) {
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
	
	public Notification() {
		
	}
	
	public int getNotificationId() {
		return notificationId;
	}
	
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	
	public int getSender() {
		return sender;
	}
	
	public void setSender(int sender) {
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
	    return "{notificationId: " + notificationId + ", sender: " + sender + ", receiver: " + receiver + "\", message: \"" + message + "\", createdAt: \"" + createdAt + "\"}";
	}
	
	@Override
	public int compareTo(Notification o) {
		if (this.getNotificationId() == o.getNotificationId())
			return 0;
		else {
			if (this.getNotificationId() > o.getNotificationId())
				return 1;
			else
				return -1;
		}
	}
	
}
