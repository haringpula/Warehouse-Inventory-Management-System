import java.sql.*;
import javax.swing.*;
import java.awt.*;
public class SQLConnection {

	private static Connection connection = null;
	private static String URL = "jdbc:mysql://localhost:3306/wims";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String user = "root";
	private static String pass = "1234";
	public static Connection connector() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(URL, user, pass);
			System.out.println("connection to database is successful");
			return connection;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
