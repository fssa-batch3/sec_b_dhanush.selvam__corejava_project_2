package in.fssa.leavepulse.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.Interface.EmployeeRoleInterface;
import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.EmployeeRole;
import in.fssa.leavepulse.util.ConnectionUtil;

public class EmployeeRoleDAO implements EmployeeRoleInterface{
	
	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<EmployeeRole> getAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EmployeeRole> employeeRoleList = null;

		try {

			String query = "SELECT * FROM employee_role WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			employeeRoleList = new ArrayList<>();

			while (rs.next()) {

				EmployeeRole empRole = new EmployeeRole();
				empRole.setEmpRoleId(rs.getInt("emp_role_id"));
				empRole.setEmployeeId(rs.getInt("employee_id"));
				empRole.setManagerId(rs.getInt("manager_id"));
				empRole.setRoleId(rs.getInt("role_id"));
				empRole.setIsActive(rs.getBoolean("is_active"));
				employeeRoleList.add(empRole);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return employeeRoleList;

	}
	
	/**
	 * 
	 * @param empRoleId
	 * @return
	 * @throws PersistenceException
	 */
	public EmployeeRole findEmpRoleByEmpRoleId(int empRoleId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		EmployeeRole empRole = null;
		
		try {
			
			String query = "SELECT * from employee_role WHERE is_active = 1 AND emp_role_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, empRoleId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				empRole = new EmployeeRole();
				empRole.setEmpRoleId(rs.getInt("emp_role_id"));
				empRole.setEmployeeId(rs.getInt("employee_id"));
				empRole.setManagerId(rs.getInt("manager_id"));
				empRole.setRoleId(rs.getInt("role_id"));
				empRole.setIsActive(rs.getBoolean("is_active"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return empRole;
		
	}
	
	/**
	 * 
	 * @param employeeId
	 * @return
	 * @throws PersistenceException
	 */
	public EmployeeRole findEmpRoleByEmployeeId(int employeeId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		EmployeeRole empRole = null;
		
		try {
			
			String query = "SELECT * from employee_role WHERE is_active = 1 AND employee_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				empRole = new EmployeeRole();
				empRole.setEmpRoleId(rs.getInt("emp_role_id"));
				empRole.setEmployeeId(rs.getInt("employee_id"));
				empRole.setManagerId(rs.getInt("manager_id"));
				empRole.setRoleId(rs.getInt("role_id"));
				empRole.setIsActive(rs.getBoolean("is_active"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return empRole;
		
	}
	
	/**
	 * @param managerId
	 * @return
	 * @exception PersistenceException
	 */
	public List<EmployeeRole> findAllEmpRoleByManagerId(int managerId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EmployeeRole> employeeRoleList = null;

		try {

			String query = "SELECT * FROM employee_role WHERE is_active = 1 AND manager_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, managerId);
			rs = ps.executeQuery();
			employeeRoleList = new ArrayList<>();

			while (rs.next()) {

				EmployeeRole empRole = new EmployeeRole();
				empRole.setEmpRoleId(rs.getInt("emp_role_id"));
				empRole.setEmployeeId(rs.getInt("employee_id"));
				empRole.setManagerId(rs.getInt("manager_id"));
				empRole.setRoleId(rs.getInt("role_id"));
				empRole.setIsActive(rs.getBoolean("is_active"));
				employeeRoleList.add(empRole);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return employeeRoleList;

	}
	
	/**
	 * 
	 * @param roleId
	 * @return
	 * @throws PersistenceException
	 */
	public List<EmployeeRole> findAllEmpRoleByRoleId(int roleId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<EmployeeRole> employeeRoleList = null;

		try {

			String query = "SELECT * FROM employee_role WHERE is_active = 1 AND role_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, roleId);
			rs = ps.executeQuery();
			employeeRoleList = new ArrayList<>();

			while (rs.next()) {

				EmployeeRole empRole = new EmployeeRole();
				empRole.setEmpRoleId(rs.getInt("emp_role_id"));
				empRole.setEmployeeId(rs.getInt("employee_id"));
				empRole.setManagerId(rs.getInt("manager_id"));
				empRole.setRoleId(rs.getInt("role_id"));
				empRole.setIsActive(rs.getBoolean("is_active"));
				employeeRoleList.add(empRole);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return employeeRoleList;

	}
	
	/**
	 * 
	 * @param employeeId
	 * @param managerId
	 * @param roleId
	 * @throws PersistenceException
	 */
	public void create(int employeeId, int managerId, int roleId) throws PersistenceException {
		
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO employee_role (employee_id, manager_id, role_id) VALUES (?, ?, ?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employeeId);
			ps.setInt(2, managerId);
			ps.setInt(3, roleId);
			ps.executeUpdate();
			
			System.out.println("New Employee-Role Created Successfully");

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
	 * @param empRoleId
	 * @param empRole
	 * @throws PersistenceException
	 */
	public void update(int empRoleId, EmployeeRole empRole) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE employee_role SET manager_id = ?, role_id = ? WHERE emp_role_id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, empRole.getManagerId());
			ps.setInt(2, empRole.getRoleId());
			ps.setInt(3, empRoleId);
			ps.executeUpdate();
			System.out.println("Employee-Role Updated Successfully");

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
	 * @param empRoleId
	 * @throws PersistenceException
	 */
	public void delete(int empRoleId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE employee_role SET is_active = 0 WHERE emp_role_id = ? AND is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, empRoleId);
			ps.executeUpdate();
			System.out.println("Employee-Role Deleted Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}

	}
	
	public int getLastEmpRoleId() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int employeeId = 0;
		try {
			String query = "SELECT emp_role_id FROM employee_role WHERE is_active = 1 ORDER BY emp_role_id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				employeeId = rs.getInt("emp_role_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//	        throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return employeeId;
	}
	
}
