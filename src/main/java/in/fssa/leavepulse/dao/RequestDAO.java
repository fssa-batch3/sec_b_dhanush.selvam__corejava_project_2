package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.dto.RequestDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.interfaces.RequestInterface;
import in.fssa.leavepulse.model.Request;
import in.fssa.leavepulse.model.Request.LeaveStatus;
import in.fssa.leavepulse.util.ConnectionUtil;

public class RequestDAO implements RequestInterface {

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<Request> getAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Request> requestList = null;

		try {

			String query = "SELECT request_id, leave_id, start_date, end_date, reason, created_by, manager_id, created_at, status, comments FROM requests WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				Request request = new Request();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				requestList.add(request);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return requestList;

	}

	/**
	 * 
	 * @param requestId
	 * @return
	 * @throws PersistenceException
	 */
	public Request findRequestByRequestId(int requestId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Request request = null;

		try {

			String query = "SELECT request_id, leave_id, start_date, end_date, reason, created_by, manager_id, created_at, status, comments FROM requests WHERE is_active = 1 AND request_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, requestId);
			rs = ps.executeQuery();

			if (rs.next()) {

				request = new Request();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return request;

	}

	/**
	 * 
	 * @param leaveId
	 * @return
	 * @throws PersistenceException
	 */
	public List<Request> findAllRequestByLeaveId(int leaveId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Request> requestList = null;

		try {

			String query = "SELECT request_id, leave_id, start_date, end_date, reason, created_by, manager_id, created_at, status, comments FROM requests WHERE is_active = 1 AND leave_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, leaveId);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				Request request = new Request();
				request = new Request();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				requestList.add(request);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return requestList;

	}

	/**
	 * 
	 * @param managerId
	 * @return
	 * @throws PersistenceException
	 */
	public List<Request> findAllRequestByManagerId(int managerId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Request> requestList = null;

		try {

			String query = "SELECT request_id, leave_id, start_date, end_date, reason, created_by, manager_id, created_at, status, comments FROM requests WHERE is_active = 1 AND manager_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, managerId);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				Request request = new Request();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				requestList.add(request);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return requestList;

	}

	/**
	 * 
	 * @param request
	 * @throws PersistenceException
	 */
	public void create(Request request) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO requests (leave_id, start_date, end_date, reason, created_by, modified_by, manager_id) VALUES (?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, request.getLeaveId());
			ps.setString(2, request.getStartDate().toString());
			ps.setString(3, request.getEndDate().toString());
			ps.setString(4, request.getReason());
			ps.setInt(5, request.getCreatedBy());
			ps.setInt(6, request.getCreatedBy());
			ps.setInt(7, request.getManagerId());
			ps.executeUpdate();

			System.out.println("New Request Created Successfully");

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
	 * @param requestId
	 * @param request
	 * @throws PersistenceException
	 */
	public void update(int requestId, Request request) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE requests SET status = ?, comments = ?, modified_by = ? WHERE is_active = 1 AND request_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, request.getLeaveStatus().name());
			ps.setString(2, request.getComments());
			ps.setInt(3, request.getModifiedBy());
			ps.setInt(4, requestId);
			ps.executeUpdate();

			System.out.println("Request Updated Successfully");

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
	 * @param requestId
	 * @throws PersistenceException
	 */
	public void delete(int requestId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE requests SET is_active = 0 WHERE is_active = 1 AND request_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, requestId);
			ps.executeUpdate();
			System.out.println("Request Deleted Successfully");

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
	public int getLastRequestId() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int requestId = 0;
		try {
			String query = "SELECT request_id FROM requests WHERE is_active = 1 ORDER BY request_id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				requestId = rs.getInt("request_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//	        throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return requestId;
	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<RequestDTO> getAllRequestWithEmployee() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RequestDTO> requestList = null;

		try {
			String query = "SELECT r.request_id, r.leave_id, r.start_date, r.end_date, r.reason, r.created_by, r.manager_id, r.created_at, r.status, r.comments, e.first_name, e.last_name, e.email, l.leave_type FROM requests r JOIN employees e ON r.created_by = e.employee_id JOIN leave_types l ON r.leave_id = l.leave_id WHERE r.is_active = 1 AND e.is_active = 1 AND l.is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				RequestDTO request = new RequestDTO();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(RequestDTO.LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				request.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
				request.setEmployeeEmail(rs.getString("email"));
				request.setLeaveType(rs.getString("leave_type"));
				requestList.add(request);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return requestList;

	}

	/**
	 * 
	 * @param requestId
	 * @return
	 * @throws PersistenceException
	 */
	public RequestDTO findRequestWithEmployeeByRequestId(int requestId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		RequestDTO request = null;

		try {
			String query = "SELECT r.request_id, r.leave_id, r.start_date, r.end_date, r.reason, r.created_by, r.manager_id, r.created_at, r.status, r.comments, e.first_name, e.last_name, e.email, l.leave_type FROM requests r JOIN employees e ON r.created_by = e.employee_id JOIN leave_types l ON r.leave_id = l.leave_id WHERE r.request_id = ? AND r.is_active = 1 AND e.is_active = 1 AND l.is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, requestId);
			rs = ps.executeQuery();

			if (rs.next()) {

				request = new RequestDTO();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(RequestDTO.LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				request.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
				request.setEmployeeEmail(rs.getString("email"));
				request.setLeaveType(rs.getString("leave_type"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return request;

	}
	
	/**
	 * 
	 * @param managerId
	 * @return
	 * @throws PersistenceException
	 */
	public List<RequestDTO> getAllRequestWithEmployeeByManagerId(int managerId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RequestDTO> requestList = null;

		try {
			String query = "SELECT r.request_id, r.leave_id, r.start_date, r.end_date, r.reason, r.created_by, r.manager_id, r.created_at, r.status, r.comments, e.first_name, e.last_name, e.email, l.leave_type FROM requests r JOIN employees e ON r.created_by = e.employee_id JOIN leave_types l ON r.leave_id = l.leave_id WHERE r.manager_id = ? AND r.is_active = 1 AND e.is_active = 1 AND l.is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, managerId);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				RequestDTO request = new RequestDTO();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(RequestDTO.LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				request.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
				request.setEmployeeEmail(rs.getString("email"));
				request.setLeaveType(rs.getString("leave_type"));
				requestList.add(request);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return requestList;

	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public List<RequestDTO> getAllRequestWithEmployeeByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RequestDTO> requestList = null;

		try {
			String query = "SELECT r.request_id, r.leave_id, r.start_date, r.end_date, r.reason, r.created_by, r.manager_id, r.created_at, r.status, r.comments, e.first_name, e.last_name, e.email, l.leave_type FROM requests r JOIN employees e ON r.created_by = e.employee_id JOIN leave_types l ON r.leave_id = l.leave_id WHERE r.created_by = ? AND r.is_active = 1 AND e.is_active = 1 AND l.is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				RequestDTO request = new RequestDTO();
				request.setRequestId(rs.getInt("request_id"));
				request.setLeaveId(rs.getInt("leave_id"));
				String start_date = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(start_date, formatter));
				String end_date = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(end_date, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedBy(rs.getInt("created_by"));
				request.setManagerId(rs.getInt("manager_id"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(RequestDTO.LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				request.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
				request.setEmployeeEmail(rs.getString("email"));
				request.setLeaveType(rs.getString("leave_type"));
				requestList.add(request);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return requestList;

	}

}
