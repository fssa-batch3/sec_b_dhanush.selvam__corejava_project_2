package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.fssa.leavepulse.dto.RequestDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Request;
import in.fssa.leavepulse.model.Request.LeaveStatus;
import in.fssa.leavepulse.util.ConnectionUtil;

public class RequestDAO {

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
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
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
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
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
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
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
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
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

			String query = "INSERT INTO requests (leave_id, start_date, end_date, reason, created_by, modified_by, manager_id, loss_of_pay) VALUES (?,?,?,?,?,?,?,?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, request.getLeaveId());
			ps.setString(2, request.getStartDate().toString());
			ps.setString(3, request.getEndDate().toString());
			ps.setString(4, request.getReason().trim());
			ps.setInt(5, request.getCreatedBy());
			ps.setInt(6, request.getCreatedBy());
			ps.setInt(7, request.getManagerId());
			ps.setString(8, request.getLossOfPay());
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
	public void cancel(int requestId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String status = LeaveStatus.Cancelled.toString();
		    String query = "UPDATE requests SET status = ? WHERE is_active = 1 AND request_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, status);
			ps.setInt(2, requestId);
			ps.executeUpdate();
			System.out.println("Request Cancelled Successfully");

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
			String query = "SELECT r.request_id, r.leave_id, r.start_date, r.end_date, r.reason, r.created_by, r.manager_id, r.created_at, r.status, r.comments, r.loss_of_pay, e.first_name, e.last_name, e.email, l.leave_type FROM requests r JOIN employees e ON r.created_by = e.employee_id JOIN leaves l ON r.leave_id = l.leave_id AND r.is_active = 1 AND e.is_active = 1 AND l.is_active = 1 ORDER BY r.request_id ASC"; 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				RequestDTO request = new RequestDTO();
				request.setRequestId(rs.getInt("request_id"));
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(RequestDTO.LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				request.setEmployeeId(rs.getInt("created_by"));
				request.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
				request.setEmployeeEmail(rs.getString("email"));
				request.setLeaveId(rs.getInt("leave_id"));
				request.setLeaveType(rs.getString("leave_type"));
				request.setLossOfPay(rs.getString("loss_of_pay"));
				requestList.add(request);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		Map<String, List<RequestDTO>> lossOfPayMap = new HashMap<>();
		List<RequestDTO> newRequestList = new ArrayList<>();
		List<RequestDTO> resultRequestList = new ArrayList<>();

		for (RequestDTO request : requestList) {
		    if (request.getLossOfPay() != null) {
		        lossOfPayMap
		            .computeIfAbsent(request.getLossOfPay(), k -> new ArrayList<>())
		            .add(request);
		    }
		}
				
		for (Map.Entry<String, List<RequestDTO>> entry : lossOfPayMap.entrySet()) {
		    List<RequestDTO> requests = entry.getValue();
		    LocalDate startDate = requests.get(0).getStartDate();
		    requests.get(requests.size() - 1).setStartDate(startDate);
		}
				
		String previousLOP = "";
				
		for (RequestDTO request : requestList) {
			
			if (request.getLossOfPay() == null) 
				newRequestList.add(request);
			
			else {
								
				if (!request.getLossOfPay().equals(previousLOP)) {
					List<RequestDTO> requests = lossOfPayMap.get(request.getLossOfPay());
					newRequestList.add(requests.get(requests.size() - 1));
					previousLOP = request.getLossOfPay();
				}
			}
		}
				
		for (int i = newRequestList.size() - 1; i >= 0; i--)
			resultRequestList.add(newRequestList.get(i));
		
		return resultRequestList;

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
			String query = "SELECT r.request_id, r.leave_id, r.start_date, r.end_date, r.reason, r.created_by, r.manager_id, r.created_at, r.status, r.comments, r.loss_of_pay, e.first_name, e.last_name, e.email, l.leave_type FROM requests r JOIN employees e ON r.created_by = e.employee_id JOIN leaves l ON r.leave_id = l.leave_id WHERE r.manager_id = ? AND r.is_active = 1 AND e.is_active = 1 AND l.is_active = 1 ORDER BY r.request_id ASC"; 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, managerId);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				RequestDTO request = new RequestDTO();
				request.setRequestId(rs.getInt("request_id"));
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(RequestDTO.LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				request.setEmployeeId(rs.getInt("created_by"));
				request.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
				request.setEmployeeEmail(rs.getString("email"));
				request.setLeaveId(rs.getInt("leave_id"));
				request.setLeaveType(rs.getString("leave_type"));
				request.setLossOfPay(rs.getString("loss_of_pay"));
				requestList.add(request);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		Map<String, List<RequestDTO>> lossOfPayMap = new HashMap<>();
		List<RequestDTO> newRequestList = new ArrayList<>();
		List<RequestDTO> resultRequestList = new ArrayList<>();

		for (RequestDTO request : requestList) {
		    if (request.getLossOfPay() != null) {
		        lossOfPayMap
		            .computeIfAbsent(request.getLossOfPay(), k -> new ArrayList<>())
		            .add(request);
		    }
		}
				
		for (Map.Entry<String, List<RequestDTO>> entry : lossOfPayMap.entrySet()) {
		    List<RequestDTO> requests = entry.getValue();
		    LocalDate startDate = requests.get(0).getStartDate();
		    requests.get(requests.size() - 1).setStartDate(startDate);
		}
				
		String previousLOP = "";
				
		for (RequestDTO request : requestList) {
			
			if (request.getLossOfPay() == null) 
				newRequestList.add(request);
			
			else {
								
				if (!request.getLossOfPay().equals(previousLOP)) {
					List<RequestDTO> requests = lossOfPayMap.get(request.getLossOfPay());
					newRequestList.add(requests.get(requests.size() - 1));
					previousLOP = request.getLossOfPay();
				}
			}
		}
				
		for (int i = newRequestList.size() - 1; i >= 0; i--)
			resultRequestList.add(newRequestList.get(i));
		
		return resultRequestList;
		
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
			String query = "SELECT r.request_id, r.leave_id, r.start_date, r.end_date, r.reason, r.created_by, r.manager_id, r.created_at, r.status, r.comments, r.loss_of_pay, e.first_name, e.last_name, e.email, l.leave_type FROM requests r JOIN employees e ON r.created_by = e.employee_id JOIN leaves l ON r.leave_id = l.leave_id WHERE r.created_by = ? AND r.is_active = 1 AND e.is_active = 1 AND l.is_active = 1 ORDER BY r.request_id ASC"; 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			requestList = new ArrayList<>();

			while (rs.next()) {

				RequestDTO request = new RequestDTO();
				request.setRequestId(rs.getInt("request_id"));
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
				request.setReason(rs.getString("reason"));
				request.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				request.setLeaveStatus(RequestDTO.LeaveStatus.valueOf(rs.getString("status")));
				request.setComments(rs.getString("comments"));
				request.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
				request.setEmployeeEmail(rs.getString("email"));
				request.setLeaveId(rs.getInt("leave_id"));
				request.setLeaveType(rs.getString("leave_type"));
				request.setLossOfPay(rs.getString("loss_of_pay"));
				requestList.add(request);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		Map<String, List<RequestDTO>> lossOfPayMap = new HashMap<>();
		List<RequestDTO> newRequestList = new ArrayList<>();
		List<RequestDTO> resultRequestList = new ArrayList<>();

		for (RequestDTO request : requestList) {
		    if (request.getLossOfPay() != null) {
		        lossOfPayMap
		            .computeIfAbsent(request.getLossOfPay(), k -> new ArrayList<>())
		            .add(request);
		    }
		}
				
		for (Map.Entry<String, List<RequestDTO>> entry : lossOfPayMap.entrySet()) {
		    List<RequestDTO> requests = entry.getValue();
		    LocalDate startDate = requests.get(0).getStartDate();
		    requests.get(requests.size() - 1).setStartDate(startDate);
		}
				
		String previousLOP = "";
				
		for (RequestDTO request : requestList) {
			
			if (request.getLossOfPay() == null) 
				newRequestList.add(request);
			
			else {
								
				if (!request.getLossOfPay().equals(previousLOP)) {
					List<RequestDTO> requests = lossOfPayMap.get(request.getLossOfPay());
					newRequestList.add(requests.get(requests.size() - 1));
					previousLOP = request.getLossOfPay();
				}
			}
		}
				
		for (int i = newRequestList.size() - 1; i >= 0; i--)
			resultRequestList.add(newRequestList.get(i));
		
		return resultRequestList;

	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public List<Request> getAllLeaveDateByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Request> dateList = null;
		String today = LocalDate.now().toString();

		try {
			String query = "SELECT start_date, end_date FROM requests WHERE is_active = 1 AND created_by = ? AND (status = 'Pending' OR status = 'Accepted') AND end_date >= '" + today + "'";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			dateList = new ArrayList<>();

			while (rs.next()) {

				Request request = new Request();
				String startDate = rs.getString("start_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				request.setStartDate(LocalDate.parse(startDate, formatter));
				String endDate = rs.getString("end_date");
				request.setEndDate(LocalDate.parse(endDate, formatter));
				dateList.add(request);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return dateList;

	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public String findEmployeeLastLossOfPayId(int employeeId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String lossOfPayId = null;

		try {

			String query = "SELECT loss_of_pay FROM requests WHERE is_active = 1 AND created_by = ? AND loss_of_pay IS NOT NULL ORDER BY request_id DESC";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();

			if (rs.next()) lossOfPayId = rs.getString("loss_of_pay");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		if (lossOfPayId == null) return null;
		else return lossOfPayId.substring((employeeId + "").length());
		
	}
	
}
