package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.dto.EmployeeDTO;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.interfaces.EmployeeInterface;
import in.fssa.leavepulse.model.Employee;
import in.fssa.leavepulse.util.ConnectionUtil;

public class EmployeeDAO implements EmployeeInterface {

	/**
	 * @return
	 * @throws PersistenceException
	 */
	public List<Employee> getAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Employee> employeesList = null;

		try {

			String query = "SELECT employee_id, first_name, last_name, email, phone_no, password, address, hire_date, is_active FROM employees WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			employeesList = new ArrayList<>();

			while (rs.next()) {

				Employee employee = new Employee();
				employee.setEmployee_id(rs.getInt("employee_id"));
				employee.setFirst_name(rs.getString("first_name"));
				employee.setLast_name(rs.getString("last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhone_no(rs.getLong("phone_no"));
				employee.setPassword(rs.getString("password"));
				employee.setAddress(rs.getString("address"));
				String hire_date = rs.getString("hire_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				employee.setHireDate(LocalDate.parse(hire_date, formatter));
//				employee.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				employee.setActive(rs.getBoolean("is_active"));
				employeesList.add(employee);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return employeesList;

	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public Employee findEmployeeByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Employee employee = null;

		try {

			String query = "SELECT employee_id, first_name, last_name, email, phone_no, password, address, hire_date, is_active FROM employees WHERE is_active = 1 AND employee_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();

			if (rs.next()) {
				employee = new Employee();
				employee.setEmployee_id(rs.getInt("employee_id"));
				employee.setFirst_name(rs.getString("first_name"));
				employee.setLast_name(rs.getString("last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhone_no(rs.getLong("phone_no"));
				employee.setPassword(rs.getString("password"));
				employee.setAddress(rs.getString("address"));
				String hire_date = rs.getString("hire_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				employee.setHireDate(LocalDate.parse(hire_date, formatter));
//				employee.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				employee.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return employee;

	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws PersistenceException
	 */
	public Employee findEmployeeByEmployeeEmail(String email) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Employee employee = null;

		try {

			String query = "SELECT employee_id, first_name, last_name, email, phone_no, password, address, hire_date, is_active FROM employees WHERE is_active = 1 AND email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				employee = new Employee();
				employee.setEmployee_id(rs.getInt("employee_id"));
				employee.setFirst_name(rs.getString("first_name"));
				employee.setLast_name(rs.getString("last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhone_no(rs.getLong("phone_no"));
				employee.setPassword(rs.getString("password"));
				employee.setAddress(rs.getString("address"));
				String hire_date = rs.getString("hire_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				employee.setHireDate(LocalDate.parse(hire_date, formatter));
//				employee.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				employee.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return employee;

	}

	/**
	 * 
	 * @param phoneNo
	 * @return
	 * @throws PersistenceException
	 */
	public Employee findEmployeeByEmployeePhoneNo(long phoneNo) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Employee employee = null;

		try {

			String query = "SELECT employee_id, first_name, last_name, email, phone_no, password, address, hire_date, is_active FROM employees WHERE is_active = 1 AND phone_no = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, phoneNo);
			rs = ps.executeQuery();

			if (rs.next()) {
				employee = new Employee();
				employee.setEmployee_id(rs.getInt("employee_id"));
				employee.setFirst_name(rs.getString("first_name"));
				employee.setLast_name(rs.getString("last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhone_no(rs.getLong("phone_no"));
				employee.setPassword(rs.getString("password"));
				employee.setAddress(rs.getString("address"));
				String hire_date = rs.getString("hire_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				employee.setHireDate(LocalDate.parse(hire_date, formatter));
//				employee.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				employee.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return employee;

	}

	/**
	 * @param employee, managerId, roleId
	 * @throws PersistenceException
	 */
	public int create(Employee employee) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int generatedId = -1;

		try {
			
			LocalDate date = LocalDate.now();
			String hire_date = date.toString();

			String query = "INSERT INTO employees (first_name, last_name, email, phone_no, password, address, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, employee.getFirst_name());
			ps.setString(2, employee.getLast_name());
			ps.setString(3, employee.getEmail());
			ps.setLong(4, employee.getPhone_no());
			ps.setString(5, employee.getPassword());
			ps.setString(6, employee.getAddress());
			ps.setString(7, hire_date);
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next())
				generatedId = rs.getInt(1);

			System.out.println("New Employee Created Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return generatedId;

	}

	/**
	 * @param employeeId, employee
	 * @throws PersistenceException
	 */
	public void update(int employeeId, Employee employee) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE employees SET first_name = ?, last_name = ?, phone_no = ?, address = ? WHERE employee_id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getFirst_name());
			ps.setString(2, employee.getLast_name());
			ps.setLong(3, employee.getPhone_no());
			ps.setString(4, employee.getAddress());
			ps.setInt(5, employeeId);
			ps.executeUpdate();
			
			System.out.println("Employee has been Updated Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
			
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * @param employeeId
	 * @throws PersistenceException
	 */
	public void delete(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE employees SET is_active = 0 WHERE employee_id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			ps.executeUpdate();
			System.out.println("Employee Deleted Successfully");

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
	public int getLastEmployeeId() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int employeeId = 0;
		try {
			String query = "SELECT employee_id FROM employees WHERE is_active = 1 ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				employeeId = rs.getInt("employee_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//	        throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return employeeId;
	}

	private static final String USERPASSWORD = "password";

	/**
	 * 
	 * @param email
	 * @param userPassword
	 * @return
	 * @throws PersistenceException
	 */
	public int passwordChecker(String email, String userPassword) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id;

		try {
			String query = "SELECT employee_id, password FROM employees WHERE is_active = 1 AND email = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, email);

			rs = ps.executeQuery();
			if (rs.next() && (!rs.getString(USERPASSWORD).equals(userPassword))) {
				throw new PersistenceException("Password mismatch");
			}

			id = rs.getInt("employee_id");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return id;

	}

	public int getTableLastEmployeeId() throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int employeeId = 0;
		try {
			String query = "SELECT employee_id FROM employees ORDER BY created_at DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				employeeId = rs.getInt("employee_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return employeeId;
	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<EmployeeDTO> getAllEmployeeWithRole() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EmployeeDTO> employeesList = null;

		try {
			String query = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone_no, e.address, e.hire_date, r.role_name, m.employee_id, m.email FROM employee_role er JOIN employees e ON er.employee_id = e.employee_id JOIN employees m ON er.manager_id = m.employee_id JOIN roles r ON er.role_id = r.role_id WHERE e.is_active = 1 AND er.is_active = 1 AND r.is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			employeesList = new ArrayList<>();

			while (rs.next()) {

				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setFirstName(rs.getString("e.first_name"));
				employee.setLastName(rs.getString("e.last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhoneNo(rs.getLong("phone_no"));
				employee.setAddress(rs.getString("address"));
				String hire_date = rs.getString("hire_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				employee.setHireDate(LocalDate.parse(hire_date, formatter));
//				employee.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				employee.setManagerId(rs.getInt("m.employee_id"));
				employee.setManagerEmail(rs.getString("m.email"));
				employee.setRoleName(rs.getString("role_name"));
				employeesList.add(employee);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return employeesList;

	}
	
	/**
	 * 
	 * @param managerId
	 * @return
	 * @throws PersistenceException
	 */
	public List<EmployeeDTO> getAllEmployeeWithRoleByManagerId(int managerId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EmployeeDTO> employeesList = null;

		try {
			String query = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone_no, e.address, e.hire_date, r.role_name, m.employee_id, m.email FROM employee_role er JOIN employees e ON er.employee_id = e.employee_id JOIN employees m ON er.manager_id = m.employee_id JOIN roles r ON er.role_id = r.role_id WHERE e.is_active = 1 AND er.is_active = 1 AND r.is_active = 1 AND er.manager_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, managerId);
			rs = ps.executeQuery();
			employeesList = new ArrayList<>();

			while (rs.next()) {

				EmployeeDTO employee = new EmployeeDTO();
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setFirstName(rs.getString("e.first_name"));
				employee.setLastName(rs.getString("e.last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhoneNo(rs.getLong("phone_no"));
				employee.setAddress(rs.getString("address"));
				String hire_date = rs.getString("hire_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				employee.setHireDate(LocalDate.parse(hire_date, formatter));
//				employee.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				employee.setManagerId(rs.getInt("m.employee_id"));
				employee.setManagerEmail(rs.getString("m.email"));
				employee.setRoleName(rs.getString("role_name"));
				employeesList.add(employee);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return employeesList;

	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public EmployeeDTO findEmployeeWithRole(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeDTO employee = null;

		try {
			
			String query = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone_no, e.address, e.password, e.hire_date, r.role_name, m.employee_id, m.email FROM employee_role er JOIN employees e ON er.employee_id = e.employee_id JOIN employees m ON er.manager_id = m.employee_id JOIN roles r ON er.role_id = r.role_id WHERE e.is_active = 1 AND er.is_active = 1 AND r.is_active = 1 AND e.employee_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();

			if (rs.next()) {

				employee = new EmployeeDTO();
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setFirstName(rs.getString("e.first_name"));
				employee.setLastName(rs.getString("e.last_name"));
				employee.setEmail(rs.getString("email"));
				employee.setPhoneNo(rs.getLong("phone_no"));
				employee.setAddress(rs.getString("address"));
				employee.setPassword(rs.getString("password"));
				String hire_date = rs.getString("hire_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				employee.setHireDate(LocalDate.parse(hire_date, formatter));
//				employee.setCreatedAt(Timestamp.valueOf(rs.getString("created_at")));
				employee.setManagerId(rs.getInt("m.employee_id"));
				employee.setManagerEmail(rs.getString("m.email"));
				employee.setRoleName(rs.getString("role_name"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return employee;

	}

}