import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class CreateAccount{

	private JFrame frame;
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextField tfPassword;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_1;
	private JComboBox<?> comboBox;
	private JTextField tfUsername;
	private JLabel lblAccessLevel;

	public static void main(String[] args) {
		new CreateAccount();
	}

	public CreateAccount() {
		initialize();
	}

	private void initialize(){
		frame = new JFrame();
		frame.setBounds(0, 0, 275, 450);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(30, 11, 163, 61);
		frame.getContentPane().add(lblNewLabel);

		tfFirstName = new JTextField();
		tfFirstName.setForeground(Color.GRAY);
		tfFirstName.setText("First Name");
		tfFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfFirstName.getText().trim().equals("First Name")) {
					tfFirstName.setText("");
					tfFirstName.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfFirstName.getText().trim().equals("") || tfFirstName.getText().trim().equals("First Name")) {
					tfFirstName.setText("First Name");
					tfFirstName.setForeground(Color.GRAY);
				}
			}
		});
		tfFirstName.setBounds(30, 100, 100, 20);
		frame.getContentPane().add(tfFirstName);
		tfFirstName.setColumns(10);

		tfLastName = new JTextField();
		tfLastName.setForeground(Color.GRAY);
		tfLastName.setText("Last Name");
		tfLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfLastName.getText().trim().equals("Last Name")) {
					tfLastName.setText("");
					tfLastName.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfLastName.getText().trim().equals("") || tfLastName.getText().trim().equals("Last Name")) {
					tfLastName.setText("Last Name");
					tfLastName.setForeground(Color.GRAY);
				}
			}
		});
		tfLastName.setColumns(10);
		tfLastName.setBounds(140, 100, 100, 20);
		frame.getContentPane().add(tfLastName);

		tfEmail = new JTextField();
		tfEmail.setForeground(Color.GRAY);
		tfEmail.setText("Email");
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfEmail.getText().trim().equals("Email")) {
					tfEmail.setText("");
					tfEmail.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfEmail.getText().trim().equals("") || tfEmail.getText().trim().equals("Email")) {
					tfEmail.setText("Email");
					tfEmail.setForeground(Color.GRAY);
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(30, 193, 210, 20);
		frame.getContentPane().add(tfEmail);

		tfPassword = new JTextField();
		tfPassword.setText("Password");
		tfPassword.setForeground(Color.GRAY);
		tfPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfPassword.getText().trim().equals("Password")) {
					tfPassword.setText("");
					tfPassword.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfPassword.getText().trim().equals("") || tfPassword.getText().trim().equals("Password")) {
					tfPassword.setText("Password");
					tfPassword.setForeground(Color.GRAY);
				}
			}
		});
		tfPassword.setColumns(10);
		tfPassword.setBounds(30, 162, 100, 20);
		frame.getContentPane().add(tfPassword);

		lblNewLabel_3 = new JLabel("It's quick and easy");
		lblNewLabel_3.setBounds(30, 57, 109, 14);
		frame.getContentPane().add(lblNewLabel_3);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAccount();
				frame.dispose();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSignUp.setBounds(84, 276, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
		lblNewLabel_1 = new JLabel("_____________________________________________________________");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(-24, 67, 446, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		String[] accessLevels = {"1", "2" ,"3"};
		comboBox = new JComboBox<Object>(accessLevels);
		comboBox.setBounds(184, 131, 56, 20);
		frame.getContentPane().add(comboBox);
		
		tfUsername = new JTextField();
		tfUsername.setForeground(Color.GRAY);
		tfUsername.setText("Username");
		tfUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfUsername.getText().trim().equals("Username")) {
					tfUsername.setText("");
					tfUsername.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfUsername.getText().trim().equals("") || tfUsername.getText().trim().equals("Username")) {
					tfUsername.setText("Username");
					tfUsername.setForeground(Color.GRAY);
				}
			}
		});
		tfUsername.setColumns(10);
		tfUsername.setBounds(30, 131, 100, 20);
		frame.getContentPane().add(tfUsername);
		
		lblAccessLevel = new JLabel("Level: ");
		lblAccessLevel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAccessLevel.setBounds(140, 131, 130, 20);
		frame.getContentPane().add(lblAccessLevel);
		
		frame.setFocusable(true);
		frame.setVisible(true);
	}
	
	public void createAccount() {
		Connection connection = SQLConnection.connector();
		try {
			String query = "insert into users values(?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, tfFirstName.getText());
			statement.setString(2, tfLastName.getText());
			statement.setString(3, tfUsername.getText());
			statement.setString(4, tfPassword.getText());
			statement.setString(5, comboBox.getSelectedItem().toString());
			statement.setString(6, tfEmail.getText());
			statement.execute();

			JOptionPane.showMessageDialog(null, "Sign Up Successful!!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Username Taken", "Error!!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
