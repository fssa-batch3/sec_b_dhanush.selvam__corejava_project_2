package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
				employee.setHire_date(LocalDate.parse(hire_date, formatter));
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
				employee.setHire_date(LocalDate.parse(hire_date, formatter));
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
				employee.setHire_date(LocalDate.parse(hire_date, formatter));
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
				employee.setHire_date(LocalDate.parse(hire_date, formatter));
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

			String query = "UPDATE employees SET first_name = ?, last_name = ?, password = ?, address = ? WHERE employee_id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, employee.getFirst_name());
			ps.setString(2, employee.getLast_name());
			ps.setString(3, employee.getPassword());
			ps.setString(4, employee.getAddress());
			ps.setInt(5, employeeId);
			ps.executeUpdate();
			System.out.println("Employee Updated Successfully");

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
			if(rs.next() && (!rs.getString(USERPASSWORD).equals(userPassword))) {
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

}
