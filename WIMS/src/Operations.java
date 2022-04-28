import java.sql.*;

import javax.swing.JFrame;

public class Operations {
	public boolean isLogin(String username, String password, String level, JFrame frame) {
		try {
			Connection connection = SQLConnection.connector();
			//Select firstName, level from users where username = ? and password = ? and level = ?
			String query = 
					"Select * from users where username = '" + username + "'and password = '" + password + "'and level = '" + level + "'";
			PreparedStatement statement  = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				LoginSession.username = result.getString("username");
				LoginSession.firstName = result.getString("firstName");
				LoginSession.lastName = result.getString("lastName");
				LoginSession.level = result.getString("level");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
