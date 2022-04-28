import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Logs {
	public void recordLog(){
		Connection connection = SQLConnection.connector();
		try {
			String query = "insert into logs values(?, ?, ?, NOW())";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, LoginSession.username);
			statement.setString(2, LoginSession.action);
			statement.setString(3, LoginSession.action_information);
			statement.execute();

			System.out.println("saved noice");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
