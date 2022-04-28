import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import java.sql.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevelOneFrame {

	private JFrame frame;
	private JTable table;
	private JScrollPane inventory_scrollpane;
	private JTextField searchTextField;
	private JComboBox<?> comboBox;
	
	public static void main(String[] args) {
		new LevelOneFrame();
	}
	
	public LevelOneFrame() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ShowDataInventory();
				ShowDataRequests();
			}
		});
		frame.setTitle("WIMS");
		frame.setBounds(0, 0, 1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_frame.png").getImage());
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 74, 984, 487);
		tabbedPane.setBackground(new Color(0x82B0D9));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 22));
		frame.getContentPane().add(tabbedPane);

		JPanel pnlInventory = new JPanel();
		pnlInventory.setBackground(new Color(0xD3D3D3));
		tabbedPane.addTab("INVENTORY", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_inventory.png"), pnlInventory, "Displays all products");
		pnlInventory.setLayout(null);

		inventory_scrollpane = new JScrollPane();
		inventory_scrollpane.setBounds(44, 68, 430, 330);
		pnlInventory.add(inventory_scrollpane);

		table = new JTable();
		inventory_scrollpane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(493, 43, 225, 170);
		pnlInventory.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Temp Label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_3.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(493, 228, 225, 170);
		pnlInventory.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_5, BorderLayout.NORTH);

		JLabel lblNewLabel_3 = new JLabel("Temp Label");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_5.add(lblNewLabel_3);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(733, 228, 225, 170);
		pnlInventory.add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.add(panel_6, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Temp Label");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(733, 43, 225, 170);
		pnlInventory.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel_4, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("Temp Label");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_4.add(lblNewLabel_2);

		JButton btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		btnRefresh.setToolTipText("Refresh");
		btnRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRefresh.setIcon(new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_refresh.png"));
		btnRefresh.setBackground(new Color(0xD3D3D3));
		btnRefresh.setBorder(BorderFactory.createEmptyBorder());
		btnRefresh.setBounds(446, 41, 28, 23);
		pnlInventory.add(btnRefresh);

		String[] columns = {"All", "ID", "Name", "Category", "Unit Price", "Stock"};
		comboBox = new JComboBox<Object>(columns);
		comboBox.setBounds(106, 42, 100, 22);
		pnlInventory.add(comboBox);

		JLabel lblSearchby = new JLabel("Search by:");
		lblSearchby.setBounds(44, 42, 62, 23);
		pnlInventory.add(lblSearchby);
		
		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(searchTextField.getText() == "") {
					refreshTable();
				}else FilterDatabase();
			}
		});
		searchTextField.setBounds(216, 41, 190, 23);
		pnlInventory.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton btnPrintInventory = new JButton();
		btnPrintInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						boolean print = table.print();
						if(!print) {
							System.out.println("Error! Di ma print!");
						}
					} catch (PrinterException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnPrintInventory.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPrintInventory.setIcon(new ImageIcon("C:\\Users\\elway\\Downloads\\printing.png"));
		btnPrintInventory.setToolTipText("Refresh");
		btnPrintInventory.setBorder(BorderFactory.createEmptyBorder());
		btnPrintInventory.setBackground(new Color(211, 211, 211));
		btnPrintInventory.setBounds(415, 41, 28, 23);
		pnlInventory.add(btnPrintInventory);

		JPanel pnlEdit = new JPanel();
		pnlEdit.setBackground(new Color(0xD3D3D3));
		tabbedPane.addTab("EDIT", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_additem.png"), pnlEdit, "Add, Edit and Remove Items");
		pnlEdit.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("You need Access Level 3 to perform this action");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(0, 115, 979, 103);
		pnlEdit.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Login as Level 3");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(375, 187, 229, 23);
		pnlEdit.add(btnNewButton);

		JPanel pnlRequests = new JPanel();
		pnlRequests.setToolTipText("Print");
		pnlRequests.setBackground(new Color(0xD3D3D3));
		tabbedPane.addTab("REQUESTS", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_orders.png"), pnlRequests, "Department Requests");
		pnlRequests.setLayout(null);
		
		JLabel lblNewLabel_5_1 = new JLabel("You need Access Level 3 to perform this action");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setBounds(0, 115, 979, 103);
		pnlRequests.add(lblNewLabel_5_1);
		
		JButton btnNewButton_1 = new JButton("Login as Level 3");
		btnNewButton_1.setBounds(375, 187, 229, 23);
		pnlRequests.add(btnNewButton_1);
		
		JPanel pnlPurchases = new JPanel();
		tabbedPane.addTab("PURCHASES", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_purchases.png"), pnlPurchases, null);

		JPanel pnlUsers = new JPanel();
		tabbedPane.addTab("USERS", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_users.png"), pnlUsers, null);
		
		JLabel lblLoggedinAs = new JLabel("Logged in as: ");
		lblLoggedinAs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoggedinAs.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblLoggedinAs.setBounds(737, 74, 83, 14);
		frame.getContentPane().add(lblLoggedinAs);
		
		JLabel lblAccess = new JLabel("Access Level: ");
		lblAccess.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccess.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblAccess.setBounds(737, 89, 83, 14);
		frame.getContentPane().add(lblAccess);
		
		JLabel lblNickname = new JLabel(LoginSession.firstName);
		lblNickname.setHorizontalAlignment(SwingConstants.LEFT);
		lblNickname.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNickname.setBounds(824, 74, 53, 14);
		frame.getContentPane().add(lblNickname);
		
		JLabel lblAccessLevel = new JLabel("Level " + LoginSession.level);
		lblAccessLevel.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccessLevel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblAccessLevel.setBounds(824, 89, 53, 14);
		frame.getContentPane().add(lblAccessLevel);
		
		LocalDate date = LocalDate.now();
		JLabel lblDate = new JLabel(date.toString());
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblDate.setBounds(846, 74, 138, 14);
		frame.getContentPane().add(lblDate);
		
		DateFormat timeFormat = new SimpleDateFormat("hh.mm aa");
    	String timeString = timeFormat.format(new Date()).toString();
		JLabel lblTime = new JLabel(timeString);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblTime.setBounds(846, 89, 138, 14);
		frame.getContentPane().add(lblTime);
		
		JLabel lblNewLabel = new JLabel("WAREHOUSE INVENTORY MANAGEMENT SYSTEM");
		lblNewLabel.setFont(new Font("Bungee", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 994, 76);
		frame.getContentPane().add(lblNewLabel);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}

	//automatically displays data when the you open the program
	private void ShowDataInventory() {
		Connection connection = SQLConnection.connector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("CATEGORY");
		model.addColumn("UNIT PRICE");
		model.addColumn("STOCK");
		try {
			String query = "select * from inventory order by PRODUCT_ID ASC";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				model.addRow(new Object[] {
						result.getInt("PRODUCT_ID"),
						result.getString("PRODUCT_NAME"),
						result.getString("PRODUCT_CATEGORY"),
						result.getFloat("PRODUCT_PRICE"),
						result.getInt("STOCK")
				});
			}

			result.close();
			statement.close();
			table.setModel(model);
			table.setEnabled(false);
			//additem_table.setEnabled(false);
			//table.setAutoCreateRowSorter(true);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uwuwu error");
		}
	}
	//nirfresh lang eme eme hahahah 
	private void refreshTable() {
		Connection connection = SQLConnection.connector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("CATEGORY");
		model.addColumn("UNIT PRICE");
		model.addColumn("STOCK");
		try {
			String query = "select * from inventory order by PRODUCT_ID ASC";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while(result.next()) {
				model.addRow(new Object[] {
						result.getObject(1, Integer.class),
						result.getString("PRODUCT_NAME"),
						result.getString("PRODUCT_CATEGORY"),
						result.getFloat("PRODUCT_PRICE"),
						result.getInt("STOCK")
				});
			}

			result.close();
			statement.close();
			table.setModel(model);
			table.setEnabled(false);
			//additem_table.setEnabled(true);

		} catch (Exception e) {
			System.out.println("huhu uwu error");
		}
	}
	//kinukuha ang data from search textfield to the sql to search a certain data
	private void FilterDatabase() {
		Connection connection = SQLConnection.connector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("CATEGORY");
		model.addColumn("UNIT PRICE");
		model.addColumn("STOCK");
		try {
			String id = comboBoxSearchCategories();
			String query = "select * from inventory where "+ id +" LIKE '%" + searchTextField.getText() +"%'";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while(result.next()) {
				model.addRow(new Object[] {
						result.getString("PRODUCT_ID"),
						result.getString("PRODUCT_NAME"),
						result.getString("PRODUCT_CATEGORY"),
						result.getString("PRODUCT_PRICE"),
						result.getString("STOCK")
				});
			}

			result.close();
			statement.close();
			table.setModel(model);
			table.setEnabled(false);

		} catch (Exception e) {
			System.out.println("uwu Error");
		}
	}
	//function siya na ilalagay ko sa FilterDatabase() function para malaman kung ano sinesearch
	private String comboBoxSearchCategories() {
		String id = "";
		switch((String) comboBox.getSelectedItem()) {
			case "All":
				id = "CONCAT(PRODUCT_ID, PRODUCT_NAME, PRODUCT_CATEGORY, PRODUCT_PRICE, STOCK)";
				break;
			case "ID":
				id = "PRODUCT_ID";
				break;
			case "Name":
				id = "PRODUCT_NAME";
				break;
			case "Category":
				id = "PRODUCT_CATEGORY";
				break;
			case "Unit Price":
				id = "PRODUCT_PRICE";
				break;
			case "Stock":
				id = "STOCK";
				break;
		}
		return id;
	}
	private void ShowDataRequests() {
		Connection connection = SQLConnection.connector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("REQUEST ID");
		model.addColumn("PRODUCT ID");
		model.addColumn("REQUESTED BY");
		model.addColumn("AMOUNT");
		model.addColumn("DATE");
		
		try {
			String query = "select * from requests";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				model.addRow(new Object[] {
						result.getInt("REQUEST_ID"),
						result.getInt("PRODUCT_ID"),
						result.getString("DEPARTMENT"),
						result.getInt("STOCK"),
						result.getString("REQUEST_DATE")
				});
			}

			result.close();
			statement.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uwu uwu error");
		}
	}
}
