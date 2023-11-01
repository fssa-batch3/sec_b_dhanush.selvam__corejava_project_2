package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.dto.LeaveBalanceDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Leave;
import in.fssa.leavepulse.model.LeaveBalance;
import in.fssa.leavepulse.util.ConnectionUtil;

public class LeaveBalanceDAO {
	
	/**
	 * 
	 * @param leaveBalId
	 * @return
	 * @throws PersistenceException
	 */
	public int checkLeaveBalanceIs(int leaveBalId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int leaveBalanceId = 0;

		try {

			String query = "SELECT leave_balance_id from leave_balance WHERE is_active = 1 AND leave_balance_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, leaveBalId);
			rs = ps.executeQuery();

			if (rs.next()) leaveBalanceId = rs.getInt("leave_balance_id");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return leaveBalanceId;

	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public List<Integer> findAllLeaveBalanceIdByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Integer> leaveBalIdList = null;

		try {

			String query = "SELECT leave_balance_id from leave_balance WHERE is_active = 1 AND employee_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			leaveBalIdList = new ArrayList<>();

			while (rs.next()) {
			 int leaveBalanceId = rs.getInt("leave_balance_id");
			 leaveBalIdList.add(leaveBalanceId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return leaveBalIdList;

	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public List<LeaveBalanceDTO> findAllLeaveBalanceByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<LeaveBalanceDTO> leaveBalList = null;

		try {

			String query = "SELECT lb.available_leave_days, l.leave_type FROM leave_balance lb JOIN leaves l ON lb.leave_id = l.leave_id WHERE lb.is_active = 1 AND l.is_active = 1 AND employee_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			leaveBalList = new ArrayList<>();

			while (rs.next()) {
			 LeaveBalanceDTO leaveBal = new LeaveBalanceDTO();
			 leaveBal.setLeaveType(rs.getString("leave_type"));
			 leaveBal.setAvailableLeaveDays(rs.getInt("available_leave_days"));
			 leaveBalList.add(leaveBal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return leaveBalList;

	}
	
	/**
	 * 
	 * @param leaveId
	 * @return
	 * @throws PersistenceException
	 */
	public List<Integer> findAllLeaveBalanceIdByLeaveId(int leaveId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Integer> leaveBalIdList = null;

		try {

			String query = "SELECT leave_balance_id from leave_balance WHERE is_active = 1 AND leave_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, leaveId);
			rs = ps.executeQuery();
			leaveBalIdList = new ArrayList<>();

			while (rs.next()) {
			 int leaveBalanceId = rs.getInt("leave_balance_id");
			 leaveBalIdList.add(leaveBalanceId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return leaveBalIdList;

	}
	
	/**
	 * 
	 * @param employeeId
	 * @param leave
	 * @throws PersistenceException
	 */
	public void create(int employeeId, Leave leave) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO leave_balance (employee_id, leave_id, available_leave_days) VALUES (?, ?, ?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			ps.setInt(2, leave.getLeaveId());
			ps.setInt(3, leave.getLeaveDays());
			ps.executeUpdate();
			System.out.println("New Leave Balance Created Successfully");

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
	 * @param type
	 * @param employeeId
	 * @param leaveId
	 * @param days
	 * @throws PersistenceException
	 */
	public void update(String type, int employeeId, int leaveId, int days) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			String query = null;
			
			if (type.equals("update")) 
				query = "UPDATE leave_balance SET available_leave_days = available_leave_days - ? WHERE is_active = 1 AND employee_id =  ? AND leave_id = ?";
			else if (type.equals("cancel"))
				query = "UPDATE leave_balance SET available_leave_days = available_leave_days + ? WHERE is_active = 1 AND employee_id =  ? AND leave_id = ?";
			
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, days);
			ps.setInt(2, employeeId);
			ps.setInt(3, leaveId);
			ps.executeUpdate();
			System.out.println("Leave-Balance Updated Successfully");
			
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
	 * @param leaveBalanceId
	 * @throws PersistenceException
	 */
	public void delete(int leaveBalanceId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE leave_balance SET is_active = 0 WHERE is_active = 1 AND leave_balance_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, leaveBalanceId);
			ps.executeUpdate();
			System.out.println("Leave-Balance Deleted Successfully");

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
	 * @param employeeId
	 * @param leaveId
	 * @return
	 * @throws PersistenceException
	 */
	public int remainingLeaveCountOfALeaveType(int employeeId, int leaveId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			
			String query = "SELECT available_leave_days FROM leave_balance WHERE is_active = 1 AND employee_id = ? AND leave_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			ps.setInt(2, leaveId);
			rs = ps.executeQuery();
			if (rs.next())
				count = rs.getInt("available_leave_days");
				
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
		
		return count;

	}
	
	public List<LeaveBalance> findAllAvailableLeavesByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<LeaveBalance> leaveList = null;

		try {

			String query = "SELECT leave_id, available_leave_days from leave_balance WHERE is_active = 1 AND employee_id = ? AND available_leave_days > 0";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			leaveList = new ArrayList<>();

			while (rs.next()) {
				LeaveBalance leave = new LeaveBalance();
				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setAvailableLeaveDays(rs.getInt("available_leave_days"));
				leaveList.add(leave);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return leaveList;

	}

}
