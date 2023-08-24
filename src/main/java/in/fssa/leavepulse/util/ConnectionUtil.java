package in.fssa.leavepulse.util;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

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

	        if (System.getenv("CI") != null) {
	            url = System.getenv("DATABASE_HOSTNAME");
	            userName = System.getenv("DATABASE_USERNAME");
	            passWord = System.getenv("DATABASE_PASSWORD");
	        } else {
	            Dotenv env = Dotenv.load();
	            url = env.get("DATABASE_HOSTNAME");
	            userName = env.get("DATABASE_USERNAME");
	            passWord = env.get("DATABASE_PASSWORD");
	        	
	        	
//	        	url = "jdbc:mysql://164.52.216.41:3306/dhanush_selvam_corejava_project";
//	        	userName = "734auowQyG4V";
//	        	passWord = "4ae14a6b-86ad-4767-989a-c49941406b0c";
	        }

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
			if (ps != null)ps.close();
			if (connection != null)connection.close();
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
			if (ps != null)ps.close();
			if (connection != null)connection.close();
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
