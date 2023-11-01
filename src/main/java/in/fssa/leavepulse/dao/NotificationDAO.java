package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.dto.NotificationDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Notification;
import in.fssa.leavepulse.util.ConnectionUtil;

public class NotificationDAO {
	
	public boolean checkNotificationIdIs(int notificationId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean check = false;

		try {

			String query = "SELECT created_at FROM notifications WHERE is_active = 1 AND notification_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, notificationId);
			rs = ps.executeQuery();
			if (rs.next()) check = true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return check;

	}
	
	public List<NotificationDTO> findAllNotificationByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<NotificationDTO> notificationList = null;

		try {

			String query = "SELECT n.notification_id, n.message, n.created_at, e.first_name, e.last_name FROM notifications n JOIN employees e ON n.sender = e.employee_id WHERE n.is_active = 1 AND e.is_active = 1 AND receiver = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			notificationList = new ArrayList<>();

			while (rs.next()) {

				NotificationDTO notification = new NotificationDTO();
				notification.setNotificationId(rs.getInt("notification_id"));
				notification.setSender(rs.getString("first_name") + " " + rs.getString("last_name"));
				notification.setMessage(rs.getString("message").charAt(0));
				notification.setCreatedAt(rs.getTimestamp("created_at"));
				notificationList.add(notification);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return notificationList;

	}
	
	public void create(Notification notification) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO notifications (sender, receiver, message) VALUES (?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, notification.getSender());
			ps.setInt(2, notification.getReceiver());
			ps.setString(3, Character.toString(notification.getMessage()));
			ps.executeUpdate();

			System.out.println("New Notification Created Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}
	
	public void delete(int notificationId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE notifications SET is_active = 0 WHERE is_active = 1 AND notification_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, notificationId);
			ps.executeUpdate();
			System.out.println("Notification Deleted Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
	}

}
