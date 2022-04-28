import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JComboBox comboBoxLevel;

	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblWIMS = new JLabel("WIMS");
		lblWIMS.setHorizontalAlignment(SwingConstants.CENTER);
		lblWIMS.setFont(new Font("Bungee", Font.PLAIN, 59));
		lblWIMS.setBounds(155, 20, 280, 100);
		frame.getContentPane().add(lblWIMS);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setToolTipText("");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUsername.setBounds(165, 112, 105, 35);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setToolTipText("");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setBounds(165, 143, 105, 35);
		frame.getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.setBounds(264, 123, 146, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(264, 154, 146, 24);
		frame.getContentPane().add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDatabase();
			}

		});
		btnLogin.setBounds(264, 228, 65, 24);
		frame.getContentPane().add(btnLogin);

		JLabel lblLevel = new JLabel("Log in as:");
		lblLevel.setHorizontalAlignment(SwingConstants.LEFT);
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLevel.setBounds(165, 178, 105, 35);
		frame.getContentPane().add(lblLevel);

		String[] levels = {"Level 3","Level 2","Level 1"};		
		comboBoxLevel = new JComboBox(levels);
		comboBoxLevel.setBounds(264, 189, 146, 22);
		frame.getContentPane().add(comboBoxLevel);

		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateAccount();
			}
		});
		btnSignUp.setBounds(334, 229, 76, 24);
		frame.getContentPane().add(btnSignUp);

		frame.setVisible(true);
	}
	private void LoginDatabase() {
		Operations operations = new Operations();
		try {
			String username = textField.getText();
			String password = new String(passwordField.getPassword());
			String level = returnLevel();
			if(operations.isLogin(username, password, level, frame)) {
				LoginSession.action = "Login";
				String name = LoginSession.firstName + " " + LoginSession.lastName;
				LoginSession.action_information = "Logged in as " + name;
				
				Logs logs = new Logs();
				logs.recordLog();
				
				if(level == "3") {
					new MainFrame();
					frame.dispose();
				}else if(level == "2") {
					new LevelTwoFrame();
					frame.dispose();
				}else if(level == "1") {
					new LevelOneFrame();
					frame.dispose();
				}
			}else 
				JOptionPane.showMessageDialog(null, "Username, Password or Access Level is Incorrect!!");

		} catch (Exception e2) {
			System.out.println("TITITITITITITITI");
		}
	}
	private String returnLevel() {
		switch(comboBoxLevel.getSelectedItem().toString()) {
		case "Level 1":
			return "1";
		case "Level 2":
			return "2";
		case "Level 3":
			return "3";
		}
		return "";
	}
}
