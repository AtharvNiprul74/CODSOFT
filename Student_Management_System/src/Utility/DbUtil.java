package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection("jdbc:mysql://localhost/stud_data", "root","root");
	}
}
