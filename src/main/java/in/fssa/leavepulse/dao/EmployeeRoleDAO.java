package in.fssa.leavepulse.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.EmployeeRole;
import in.fssa.leavepulse.util.ConnectionUtil;

public class EmployeeRoleDAO  {
	
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
	public EmployeeRole findEmployeeRoleByEmpRoleId(int empRoleId) throws PersistenceException {

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
	public EmployeeRole findEmployeeRoleByEmployeeId(int employeeId) throws PersistenceException {

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
	
}
