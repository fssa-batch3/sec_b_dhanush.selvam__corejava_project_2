package in.fssa.leavepulse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.leavepulse.exception.PersistenceException;
import in.fssa.leavepulse.model.Role;
import in.fssa.leavepulse.util.ConnectionUtil;

public class RoleDAO {

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<Role> getAll() throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Role> rolesList = null;

		try {

			String query = "SELECT role_id, role_name FROM roles WHERE is_active = 1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			rolesList = new ArrayList<>();

			while (rs.next()) {

				Role role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
				rolesList.add(role);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return rolesList;

	}

	/**
	 * 
	 * @param roleId
	 * @return
	 * @throws PersistenceException
	 */
	public Role findRoleByRoleId(int roleId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Role role = null;

		try {

			String query = "SELECT role_id, role_name, is_active from roles WHERE is_active = 1 AND role_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, roleId);
			rs = ps.executeQuery();

			if (rs.next()) {
				role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return role;

	}

	/**
	 * 
	 * @param roleName
	 * @return
	 * @throws PersistenceException
	 */
	public Role findRoleByRoleName(String roleName) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Role role = null;

		try {

			String query = "SELECT role_id, role_name, is_active from roles WHERE is_active = 1 AND role_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, roleName);
			rs = ps.executeQuery();

			if (rs.next()) {
				role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return role;

	}

	/**
	 * 
	 * @param roleName
	 * @throws PersistenceException
	 */
	public void create(String roleName) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "INSERT INTO roles (role_name) VALUES (?)";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, roleName);
			ps.executeUpdate();
			System.out.println("New Role Created Successfully");

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
	 * @param roleId
	 * @param roleName
	 * @throws PersistenceException
	 */
	public void update(int roleId, String roleName) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE roles SET role_name = ? WHERE is_active = 1 AND role_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, roleName);
			ps.setInt(2, roleId);
			ps.executeUpdate();
			System.out.println("Role Updated Successfully");

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
	 * @param roleId
	 * @throws PersistenceException
	 */
	public void delete(int roleId) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE roles SET is_active = 0 WHERE is_active = 1 AND role_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, roleId);
			ps.executeUpdate();
			System.out.println("Role Deleted Successfully");

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
	public int getLastRoleId() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int roleId = 0;
		try {
			String query = "SELECT role_id FROM roles WHERE is_active = 1 ORDER BY role_id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) 
				roleId = rs.getInt("role_id");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//	        throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return roleId;
	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public int getTableLastRoleId() throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int roleId = 0;
		try {
			String query = "SELECT role_id FROM roles ORDER BY role_id DESC LIMIT 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next())
				roleId = rs.getInt("role_id");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	        throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return roleId;
	}

}
