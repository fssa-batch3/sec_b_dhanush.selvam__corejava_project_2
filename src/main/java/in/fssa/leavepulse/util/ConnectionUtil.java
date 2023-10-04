package in.fssa.leavepulse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		Connection connection = null;
		String url;
		String userName;
		String passWord;

//		 url = System.getenv("DATABASE_HOSTNAME");
//		 userName = System.getenv("DATABASE_USERNAME");
//		 passWord = System.getenv("DATABASE_PASSWORD");
		
		url = "jdbc:mysql://localhost:3306/leavepulse";
		userName = "root";
		passWord = "123456";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, passWord);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return connection;

	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 */
	public static void close(Connection connection, PreparedStatement ps) {

		try {
			if (ps != null)
				ps.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {

		try {
			if (ps != null)
				ps.close();
			if (connection != null)
				connection.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
