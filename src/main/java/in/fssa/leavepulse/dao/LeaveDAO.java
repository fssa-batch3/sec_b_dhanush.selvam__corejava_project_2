package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.util.ConnectionUtil;

public class LeaveDAO {

	/**
	 * @return
	 * @throws PersistenceException
	 */
	public List<Leave> getAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Leave> leaveTypeList = null;

		try {

			String query = "SELECT leave_id, leave_type, leave_days FROM leaves WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			leaveTypeList = new ArrayList<>();

			while (rs.next()) {

				Leave leave = new Leave();
				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setLeaveDays(rs.getInt("leave_days"));
				leaveTypeList.add(leave);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return leaveTypeList;

	}

	/**
	 * 
	 * @param leaveId
	 * @return
	 * @throws PersistenceException
	 */
	public Leave findLeaveByLeaveId(int leaveId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Leave leave = null;

		try {

			String query = "SELECT leave_id, leave_type, leave_days from leaves WHERE is_active = 1 AND leave_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, leaveId);
			rs = ps.executeQuery();

			if (rs.next()) {
				leave = new Leave();
				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setLeaveDays(rs.getInt("leave_days"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return leave;

	}

	/**
	 * 
	 * @param leaveType
	 * @return
	 * @throws PersistenceException
	 */
	public Leave findLeaveByLeaveType(String leaveType) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Leave leave = null;

		try {

			String query = "SELECT leave_id, leave_type, leave_days from leaves WHERE is_active = 1 AND leave_type = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, leaveType);
			rs = ps.executeQuery();

			if (rs.next()) {
				leave = new Leave();
				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setLeaveDays(rs.getInt("leave_days"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return leave;

	}

	/**
	 * @param leave
	 * @throws PersistenceException
	 */
	public Leave create(Leave leave) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    Leave createdLeave = null;
		
		try {

			String query = "INSERT INTO leaves (leave_type, leave_days) VALUES (?, ?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, leave.getLeaveType());
			ps.setInt(2, leave.getLeaveDays());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                createdLeave = new Leave();
                createdLeave.setLeaveId(generatedId);
                createdLeave.setLeaveDays(leave.getLeaveDays());
            }
            
			System.out.println("New Leave Type created Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
		return createdLeave;
		
	}

	/**
	 * @param leaveId, leave
	 * @throws PersistenceException
	 */
	public void update(int leaveId, Leave leave) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE leaves SET leave_type = ?, leave_days = ? WHERE is_active = 1 AND leave_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, leave.getLeaveType());
			ps.setInt(2, leave.getLeaveDays());
			ps.setInt(3, leaveId);
			ps.executeUpdate();
			System.out.println("Leave Type updated Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * @param leaveId
	 * @throws PersistenceException
	 */
	public void delete(int leaveId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE leaves SET is_active = 0 WHERE is_active = 1 AND leave_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, leaveId);
			ps.executeUpdate();
			System.out.println("Leave Type Deleted Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * 
	 * @return
	 */
	public int getLastLeaveId() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int leaveiId = 0;
		try {
			String query = "SELECT leave_id FROM leaves WHERE is_active = 1 ORDER BY leave_id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				leaveiId = rs.getInt("leave_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//	        throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return leaveiId;
	}
	
	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public int getTableLastLeaveId() throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int leaveiId = 0;
		try {
			String query = "SELECT leave_id FROM leaves ORDER BY leave_id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				leaveiId = rs.getInt("leave_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	        throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return leaveiId;
	}

}
