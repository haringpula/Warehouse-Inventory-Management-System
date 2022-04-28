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

import com.jtattoo.plaf.mint.MintLookAndFeel;

import de.javasoft.synthetica.bluelight.SyntheticaBlueLightLookAndFeel;

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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame {

	private JFrame frame;
	private JTable table, additem_table;
	private JScrollPane inventory_scrollpane;
	private JTextField tfStock, tfPrice, tfName, tfID, searchTextField;
	private JComboBox<?> comboBoxCategory, comboBox;
	private JScrollPane scrollPane;
	private JTable order_table;
	private JTable log_table;
	private JTable user_table;
	private JScrollPane scrollpane_log;

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		try {
			UIManager.setLookAndFeel(new com.jtattoo.plaf.mint.MintLookAndFeel());
		} catch (Exception e) {
			// TODO: handle exception
		}
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ShowDataInventory();
				ShowDataRequests();
				showLogs();
				showUserDatabase();
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

		JLabel lblNewLabel_1 = new JLabel("Total Products");
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

		JLabel lblNewLabel_2 = new JLabel("Stocks per Category");
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
		tabbedPane.addTab("EDIT", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_additem.png"), pnlEdit, "Add, Edit and Remove Items");
		pnlEdit.setLayout(null);

		JLabel lblID = new JLabel("Product ID: ");
		lblID.setBounds(36, 25, 120, 21);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlEdit.add(lblID);

		JLabel lblName = new JLabel("Product Name:");
		lblName.setBounds(36, 55, 120, 21);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlEdit.add(lblName);

		JLabel lblCategory = new JLabel("Product Category:");
		lblCategory.setBounds(36, 85, 120, 21);
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlEdit.add(lblCategory);

		JLabel lblPrice = new JLabel("Product Price:");
		lblPrice.setBounds(36, 115, 120, 21);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlEdit.add(lblPrice);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(36, 145, 120, 21);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlEdit.add(lblStock);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(166, 187, 80, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SavetoDatabase();
				refreshTable();
				refreshLog();
				tfID.setText("");
				tfName.setText("");
				tfPrice.setText("");
				tfStock.setText("");
			}
		});
		pnlEdit.add(btnAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteData();
				refreshTable();
				refreshLog();
			}
		});
		btnRemove.setBounds(256, 187, 80, 23);
		pnlEdit.add(btnRemove);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateData();
				refreshTable();
				refreshLog();
			}
		});
		btnUpdate.setBounds(346, 187, 80, 23);
		pnlEdit.add(btnUpdate);

		tfStock = new JTextField();
		tfStock.setBounds(166, 147, 260, 20);
		tfStock.setColumns(10);
		pnlEdit.add(tfStock);

		tfPrice = new JTextField();
		tfPrice.setBounds(166, 117, 260, 20);
		tfPrice.setColumns(10);
		pnlEdit.add(tfPrice);

		String[] categories = {"Category A", "Category B", "Category C", "Category D"};
		comboBoxCategory = new JComboBox<Object>(categories);
		comboBoxCategory.setBounds(166, 86, 260, 22);
		pnlEdit.add(comboBoxCategory);

		tfName = new JTextField();
		tfName.setBounds(166, 57, 260, 20);
		tfName.setColumns(10);
		pnlEdit.add(tfName);

		tfID = new JTextField();
		//		tfID.setEditable(false);
		tfID.setEditable(false);
		tfID.setBounds(166, 27, 260, 20);
		tfID.setColumns(10);
		pnlEdit.add(tfID);

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//				String product_id = additem_table.getModel().getValueAt(additem_table.getSelectedRow(), 0).toString();
				//				SetTextField(product_id);
			}
		});
		scrollPane.setBounds(36, 221, 390, 193);
		pnlEdit.add(scrollPane);

		additem_table = new JTable();
		additem_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SetTextField();
			}
		});
		scrollPane.setViewportView(additem_table);

		JPanel pnlRequests = new JPanel();
		pnlRequests.setToolTipText("Print");
		pnlRequests.setBackground(new Color(0xD3D3D3));
		tabbedPane.addTab("REQUESTS", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_orders.png"), pnlRequests, "Department Requests");
		pnlRequests.setLayout(null);

		JButton btnPrintRequests = new JButton("");
		btnPrintRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean print = order_table.print();
					if(!print) {
						System.out.println("Error! Di ma print!");
					}
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnPrintRequests.setToolTipText("Print");
		btnPrintRequests.setIcon(new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_printer.png"));
		btnPrintRequests.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPrintRequests.setBackground(new Color(0xD3D3D3));
		btnPrintRequests.setBorder(BorderFactory.createEmptyBorder());
		btnPrintRequests.setBounds(654, 25, 40, 40);
		pnlRequests.add(btnPrintRequests);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 25, 614, 391);
		pnlRequests.add(scrollPane_1);

		order_table = new JTable();
		scrollPane_1.setViewportView(order_table);

		JPanel pnlPurchases = new JPanel();
		tabbedPane.addTab("ORDERS", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_purchases.png"), pnlPurchases, null);

		JPanel pnlUsers = new JPanel();
		tabbedPane.addTab("USERS", new ImageIcon("C:\\Users\\elway\\test\\Final\\icon_users.png"), pnlUsers, null);
		pnlUsers.setLayout(null);

		scrollpane_log = new JScrollPane();
		scrollpane_log.setBounds(41, 39, 603, 376);
		pnlUsers.add(scrollpane_log);

		log_table = new JTable();
		scrollpane_log.setViewportView(log_table);

		JScrollPane scrollpane_users = new JScrollPane();
		scrollpane_users.setBounds(651, 226, 274, 189);
		pnlUsers.add(scrollpane_users);

		user_table = new JTable();
		scrollpane_users.setViewportView(user_table);

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

		JLabel lblTitle = new JLabel("Warehouse Inventory Management System");
		lblTitle.setFont(new Font("Bungee", Font.PLAIN, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 994, 75);
		frame.getContentPane().add(lblTitle);

		JButton btnLogout = new JButton("");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logout();
			}
		});
		btnLogout.setBorder(BorderFactory.createEmptyBorder());
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setIcon(new ImageIcon("C:\\Users\\elway\\Downloads\\icon_logout.png"));
		btnLogout.setBounds(906, 22, 45, 41);
		frame.getContentPane().add(btnLogout);

		System.out.println(LoginSession.firstName);

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
			additem_table.setModel(model);
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
			additem_table.setModel(model);
			//additem_table.setEnabled(true);

		} catch (Exception e) {
			System.out.println("huhu uwu error");
		}
	}
	//saves/adds a new data to the database/ jtables
	private void SavetoDatabase() {
		Connection connection = SQLConnection.connector();
		try {
			String query = "insert into inventory values(?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, tfID.getText());
			statement.setString(2, tfName.getText());
			statement.setString(3, (String) comboBoxCategory.getSelectedItem());
			statement.setString(4, tfPrice.getText());
			statement.setString(5, tfStock.getText());
			statement.execute();

			LoginSession.action = "Add an item";
			LoginSession.action_information = 
					"Added " + "(" + tfID.getText() + ", " + tfName.getText() + ", " + (String) comboBoxCategory.getSelectedItem() + ", " + tfPrice.getText() + ", " + tfStock.getText() + ")";

			Logs logs = new Logs();
			logs.recordLog();

			System.out.println("saved noice");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, new JLabel("Kulit mo, ayusin mo kasi!", JLabel.CENTER), "Error!", JOptionPane.ERROR_MESSAGE);
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
	private void SetTextField() {
		Connection connection = SQLConnection.connector();
		try {
			int row = additem_table.getSelectedRow();
			String product_id = (additem_table.getModel().getValueAt(row, 0).toString());
			String query = "select * from inventory where PRODUCT_ID = '" + product_id + "'";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				String id = result.getString("PRODUCT_ID");
				tfID.setText(id);
				String name = result.getString("PRODUCT_NAME");
				tfName.setText(name);
				String category = result.getString("PRODUCT_CATEGORY");
				comboBoxCategory.setSelectedItem(category);;
				String price = result.getString("PRODUCT_PRICE");
				tfPrice.setText(price);
				String stock = result.getString("STOCK");
				tfStock.setText(stock);
			}
			result.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void UpdateData() {
		Connection connection = SQLConnection.connector();
		try {
			int row = additem_table.getSelectedRow();
			String product_id = (additem_table.getModel().getValueAt(row, 0).toString());

			String query = "update inventory set PRODUCT_NAME = ?, PRODUCT_CATEGORY = ?, PRODUCT_PRICE = ?, STOCK = ? WHERE PRODUCT_ID = '" + product_id + "'";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, tfName.getText());
			statement.setString(2, comboBoxCategory.getSelectedItem().toString());
			statement.setString(3, tfPrice.getText());
			statement.setString(4, tfStock.getText());
			
			LoginSession.action = "Update an item";
			LoginSession.action_information = 
					"Updated " + "(" + tfID.getText() + ", " + tfName.getText() + ", " + (String) comboBoxCategory.getSelectedItem() + ", " + tfPrice.getText() + ", " + tfStock.getText() + ")";

			Logs logs = new Logs();
			logs.recordLog();
			statement.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//deletes data
	private void DeleteData() {
		Connection connection = SQLConnection.connector();
		int row = additem_table.getSelectedRow();
		String id = additem_table.getModel().getValueAt(row, 0).toString();
		String name = additem_table.getModel().getValueAt(row, 1).toString();
		String category = additem_table.getModel().getValueAt(row, 2).toString();
		String price = additem_table.getModel().getValueAt(row, 3).toString();
		String stock = additem_table.getModel().getValueAt(row, 4).toString();
		try {
			String query = "delete from inventory where PRODUCT_ID = " + id;
			PreparedStatement statement = connection.prepareStatement(query);
			statement.execute();
			System.out.println("Deleted noice");

			LoginSession.action = "Delete an item";
			LoginSession.action_information = 
					"Deleted " + "(" + id + ", " + name + ", " + category + ", " + price + ", " + stock + ")";

			Logs logs = new Logs();
			logs.recordLog();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, new JLabel("Not Deleted!!!", JLabel.CENTER), "Error!", JOptionPane.ERROR_MESSAGE);
		}
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
			order_table.setModel(model);
			order_table.setEnabled(false);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uwu uwu error");
		}
	}

	private void showLogs() {
		Connection connection = SQLConnection.connector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("USERNAME");
		model.addColumn("ACTION");
		model.addColumn("ACTION INFORMATION");
		model.addColumn("TIME");
		try {
			String query = "select * from logs";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while(result.next()) {
				model.addRow(new Object[] {
						result.getString("USERNAME"),
						result.getString("ACTION_PERFORMED"),
						result.getString("INFORMATION"),
						result.getString("LOG_DATE")
				});
			}

			result.close();
			statement.close();
			log_table.setModel(model);
			log_table.setEnabled(false);
			log_table.setAutoResizeMode(0);
			log_table.getColumnModel().getColumn(0).setPreferredWidth(100);
			log_table.getColumnModel().getColumn(1).setPreferredWidth(100);
			log_table.getColumnModel().getColumn(2).setPreferredWidth(260);
			log_table.getColumnModel().getColumn(3).setPreferredWidth(150);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uwuwu error");
		}
	}

	public void refreshLog() {
		Connection connection = SQLConnection.connector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("USERNAME");
		model.addColumn("ACTION");
		model.addColumn("ACTION INFORMATION");
		model.addColumn("TIME");
		try {
			String query = "select * from logs";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while(result.next()) {
				model.addRow(new Object[] {
						result.getString("username"),
						result.getString("action_performed"),
						result.getString("information"),
						result.getString("log_date")
				});
			}

			result.close();
			statement.close();
			log_table.setModel(model);
			log_table.setEnabled(false);
			log_table.setAutoResizeMode(0);
			log_table.getColumnModel().getColumn(0).setPreferredWidth(100);
			log_table.getColumnModel().getColumn(1).setPreferredWidth(100);
			log_table.getColumnModel().getColumn(2).setPreferredWidth(260);
			log_table.getColumnModel().getColumn(3).setPreferredWidth(150);

		} catch (Exception e) {
			System.out.println("huhu uwu error");
		}
	}

	private void showUserDatabase() {
		Connection connection = SQLConnection.connector();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Last Name");
		model.addColumn("First Name");
		model.addColumn("Access Level");
		model.addColumn("Email");
		try {
			String query = "select * from users";
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);

			while(result.next()) {
				model.addRow(new Object[] {
						result.getString("lastName"),
						result.getString("firstName"),
						result.getString("level"),
						result.getString("email")
				});
			}

			result.close();
			statement.close();
			user_table.setModel(model);
			user_table.setEnabled(false);
			user_table.setAutoResizeMode(0);
			user_table.getColumnModel().getColumn(0).setPreferredWidth(100);
			user_table.getColumnModel().getColumn(1).setPreferredWidth(100);
			user_table.getColumnModel().getColumn(2).setPreferredWidth(110);
			user_table.getColumnModel().getColumn(3).setPreferredWidth(170);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uwuwu error");
		}
	}

	public void Logout() {
		String name = LoginSession.firstName + " " + LoginSession.lastName;

		new Login();
		frame.dispose();

		LoginSession.action = "Logout";
		LoginSession.action_information = "Logged out as " + name;

		Logs logs = new Logs();
		logs.recordLog();
	}
}
