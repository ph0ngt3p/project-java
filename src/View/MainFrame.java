package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.EmployeeBusiness;
import Controller.ExpenseBusiness;
import Controller.ProductBusiness;
import Controller.TransactionBusiness;
import Model.Bag;
import Model.Clothes;
import Model.Employee;
import Model.Expense;
import Model.Jewellery;
import Model.ProductGroup;
import Model.Salary;
import Model.Shoes;
import Model.Transaction;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public Employee currentUser;
	private JLabel txtId;
	private JLabel txtName;
	private JLabel txtDob;
	private JLabel txtPhone;
	private JLabel txtUsername;
	private JLabel txtType;
	private JButton btnDeleteEmployee;
	private JButton btnAddEmployee;
	private JButton btnUpdateEmployee;
	private DefaultTableModel productTableModel;
	private DefaultTableModel transactionTableModel;
	private DefaultTableModel employeeTableModel;
	private DefaultTableModel expenseTableModel;

	public MainFrame() {
		setTitle("Fashion Shop Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		
		// content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Define Tabbed Pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Consolas", Font.BOLD, 13));
		tabbedPane.setBounds(10, 11, 964, 515);
		contentPane.add(tabbedPane);
		
		// menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Account");
		setJMenuBar(menuBar);
		menuBar.add(menu1);
		JMenuItem changePassword = new JMenuItem("Change password");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameUpdateEmployee frame = new FrameUpdateEmployee(getFrame());
				frame.displayAccountInformation(currentUser);
				frame.setVisible(true);
			}
		});
		menu1.add(changePassword);
		JMenuItem logout = new JMenuItem("Log out");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameLogIn frame = new FrameLogIn();
				frame.setVisible(true);
				getFrame().dispose();
			}
		});
		menu1.add(logout);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new CancelActionListener());
		menu1.add(exit);
		JMenu menu2 = new JMenu("Extract");
		JMenuItem extract = new JMenuItem("Report");
		extract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ReportExtract frame = new ReportExtract();
				frame.setVisible(true);
			}
		});
		menu2.add(extract);
		menuBar.add(menu2);
		
		// Information Tab
		Font normalFont = new Font("Consolas", Font.PLAIN, 12);
		Font boldFont = new Font("Consolas", Font.BOLD, 14);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("Account Information", null, panel, null);
		
		JLabel lblEmployeesId = new JLabel("Employee's ID:");
		lblEmployeesId.setFont(normalFont);
		lblEmployeesId.setBounds(60, 50, 100, 20);
		panel.add(lblEmployeesId);
		
		txtId = new JLabel("");
		txtId.setFont(boldFont);
		txtId.setBounds(190, 50, 100, 20);
		panel.add(txtId);
		
		JLabel lblFullName = new JLabel("Full name:");
		lblFullName.setFont(normalFont);
		lblFullName.setBounds(60, 95, 100, 20);
		panel.add(lblFullName);
		
		txtName = new JLabel("");
		txtName.setFont(boldFont);
		txtName.setBounds(190, 95, 300, 20);
		panel.add(txtName);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth:");
		lblDateOfBirth.setFont(normalFont);
		lblDateOfBirth.setBounds(60, 140, 100, 20);
		panel.add(lblDateOfBirth);
		
		txtDob = new JLabel("");
		txtDob.setFont(boldFont);
		txtDob.setBounds(190, 140, 100, 20);
		panel.add(txtDob);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setFont(normalFont);
		lblPhoneNumber.setBounds(60, 185, 100, 20);
		panel.add(lblPhoneNumber);
		
		txtPhone = new JLabel("");
		txtPhone.setFont(boldFont);
		txtPhone.setBounds(190, 185, 100, 20);
		panel.add(txtPhone);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(normalFont);
		lblUsername.setBounds(60, 230, 100, 20);
		panel.add(lblUsername);
		
		txtUsername = new JLabel("");
		txtUsername.setFont(boldFont);
		txtUsername.setBounds(190, 230, 100, 20);
		panel.add(txtUsername);
		
		JLabel lblAccountType = new JLabel("Account type:");
		lblAccountType.setFont(normalFont);
		lblAccountType.setBounds(60, 275, 100, 20);
		panel.add(lblAccountType);
		
		txtType = new JLabel("");
		txtType.setFont(boldFont);
		txtType.setBounds(190, 275, 200, 20);
		panel.add(txtType);
		
		// Product Tab
		JPanel productPanel = new JPanel();
		productPanel.setLayout(null);
		tabbedPane.addTab("Product Management", null, productPanel, null);
		
		JLabel lblSearchProduct = new JLabel("Search:");
		lblSearchProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblSearchProduct.setBounds(30, 24, 60, 30);
		productPanel.add(lblSearchProduct);
		
		JTextField txtSearchProduct = new JTextField();
		txtSearchProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtSearchProduct.setBounds(100, 25, 250, 30);
		productPanel.add(txtSearchProduct);
		
		JLabel lblFilterProduct = new JLabel("Filter:");
		lblFilterProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblFilterProduct.setBounds(397, 24, 60, 30);
		productPanel.add(lblFilterProduct);
		
		JComboBox<String> cbbFilterProduct = new JComboBox<String>();
		cbbFilterProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		cbbFilterProduct.setModel(new DefaultComboBoxModel<String>(new String[] {"Product's ID", "Name", "Color", "Type"}));
		cbbFilterProduct.setBounds(467, 24, 113, 30);
		productPanel.add(cbbFilterProduct);
		
		JButton btnSearchProduct = new JButton("Search");
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String filter = cbbFilterProduct.getSelectedItem().toString();
				String key = txtSearchProduct.getText();
				ArrayList<ProductGroup> list = new ArrayList<ProductGroup>();
				for (ProductGroup e: ProductBusiness.list) {
					if (filter.equals("Product's ID")) {
						if (Integer.toString(e.getProduct().getProductId()).indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					} 
					else if (filter.equals("Name")) {
						if (e.getProduct().getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
					else if (filter.equals("Color")) {
						if (e.getProduct().getColor().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
					else if (filter.equals("Type")) {
						if (e.getProduct().getClass().getSimpleName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
				}
				displayProductTable(list);
			}
		});
		btnSearchProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnSearchProduct.setBounds(612, 24, 80, 30);
		productPanel.add(btnSearchProduct);
		
		JButton btnRefreshProductTable = new JButton("Refresh");
		btnRefreshProductTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayProductTable(ProductBusiness.list);
				txtSearchProduct.setText(null);
			}
		});
		btnRefreshProductTable.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshProductTable.setBounds(714, 24, 85, 30);
		productPanel.add(btnRefreshProductTable);
		
		JScrollPane productScrollPane = new JScrollPane();
		productScrollPane.setBounds(30, 80, 900, 330);
		
		String[] productColumnNames = {"Type", "Product ID", "Name", "Color", "Size", "Material", "Price", "Quantity"};
		productTableModel = new DefaultTableModel(productColumnNames, 0);
		JTable productTable = new JTable(productTableModel);
		displayProductTable(ProductBusiness.list);
		productTable.setFont(new Font("Consolas", Font.PLAIN, 12));
		productScrollPane.setViewportView(productTable);
		productPanel.add(productScrollPane);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnCancel.addActionListener(new CancelActionListener());
		btnCancel.setBounds(850, 435, 80, 30);
		productPanel.add(btnCancel);
		
		JButton btnAddProduct = new JButton("Add");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameAddProduct frame = new FrameAddProduct(getFrame());
				frame.setVisible(true);
			}
		});
		btnAddProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnAddProduct.setBounds(580, 435, 80, 30);
		productPanel.add(btnAddProduct);
		
		JButton btnUpdateProduct = new JButton("Update");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int[] selectedRows = productTable.getSelectedRows();
				if (selectedRows.length != 1) {
					JOptionPane.showMessageDialog(contentPane, "Please select exactly one row to update!");
				}
				else {
						int id = Integer.parseInt(productTable.getValueAt(selectedRows[0], 1).toString());
						FrameUpdateProduct frame = new FrameUpdateProduct(getFrame());
						frame.displayInformation(ProductBusiness.getGroupById(id));
						frame.setVisible(true);
				}
			}
		});
		btnUpdateProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnUpdateProduct.setBounds(670, 435, 80, 30);
		productPanel.add(btnUpdateProduct);
		
		JButton btnDeleteProduct = new JButton("Delete");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int[] selectedRows = productTable.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
				}
				else {
					for (int i = 0; i < selectedRows.length; i++) {
						int id = Integer.parseInt(productTable.getValueAt(selectedRows[i], 1).toString());
						ProductBusiness.deleteProduct(id);
					}
					displayProductTable(ProductBusiness.list);
				}
			}
		});
		btnDeleteProduct.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnDeleteProduct.setBounds(760, 435, 80, 30);
		productPanel.add(btnDeleteProduct);
		
		JButton btnAddTrans = new JButton("Add transaction");
		btnAddTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int[] selectedRows = productTable.getSelectedRows();
				if (selectedRows.length != 1) {
					JOptionPane.showMessageDialog(contentPane, "Please select a product to transact!");
				}
				else {
					int id = Integer.parseInt(productTable.getValueAt(selectedRows[0], 1).toString());
					FrameNewTransaction frame = new FrameNewTransaction(getFrame());
					frame.txtProductId.setText("" + id);
					frame.setVisible(true);
				}
			}
		});
		btnAddTrans.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnAddTrans.setBounds(30, 435, 144, 30);
		productPanel.add(btnAddTrans);
		
		// Employee panel
		JPanel employeePanel = new JPanel();
		tabbedPane.addTab("Employee Management", null, employeePanel, null);
		employeePanel.setLayout(null);
		
		JLabel lblSearchEmployee = new JLabel("Search:");
		lblSearchEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblSearchEmployee.setBounds(30, 24, 60, 30);
		employeePanel.add(lblSearchEmployee);
		
		JTextField txtSearchEmployee = new JTextField();
		txtSearchEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtSearchEmployee.setBounds(100, 25, 250, 30);
		employeePanel.add(txtSearchEmployee);
		
		JLabel lblFilterEmployee = new JLabel("Filter:");
		lblFilterEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblFilterEmployee.setBounds(397, 24, 60, 30);
		employeePanel.add(lblFilterEmployee);
		
		JComboBox<String> cbbFilterEmployee = new JComboBox<String>();
		cbbFilterEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		cbbFilterEmployee.setModel(new DefaultComboBoxModel<String>(new String[] {"Employee's ID", "Name", "Username", "Account Type"}));
		cbbFilterEmployee.setBounds(467, 24, 113, 30);
		employeePanel.add(cbbFilterEmployee);
		
		JButton btnSearchEmployee = new JButton("Search");
		btnSearchEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String filter = cbbFilterEmployee.getSelectedItem().toString();
				String key = txtSearchEmployee.getText();
				ArrayList<Employee> list = new ArrayList<Employee>();
				for (Employee e: EmployeeBusiness.list) {
					if (filter.equals("Employee's ID")) {
						if (Integer.toString(e.getEmployeeId()).indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					} 
					else if (filter.equals("Name")) {
						if (e.getName().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
					else if (filter.equals("Username")) {
						if (e.getUsername().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
					else if (filter.equals("Account Type")) {
						if (e.getAccountType().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
				}
				displayEmployeeTable(list);
			}
		});
		btnSearchEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnSearchEmployee.setBounds(612, 24, 80, 30);
		employeePanel.add(btnSearchEmployee);
		
		JButton btnRefreshEmployeeTable = new JButton("Refresh");
		btnRefreshEmployeeTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				displayEmployeeTable(EmployeeBusiness.list);
				txtSearchEmployee.setText(null);
			}
		});
		btnRefreshEmployeeTable.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshEmployeeTable.setBounds(714, 24, 85, 30);
		employeePanel.add(btnRefreshEmployeeTable);
		
		JScrollPane employeeScrollPane = new JScrollPane();
		employeeScrollPane.setBounds(30, 80, 900, 330);
		employeePanel.add(employeeScrollPane);
		
		String[] employeeColumnNames = {"Employee's ID", "Full name", "Date of birth", "Phone number", "Username", "Account Type"};
		employeeTableModel = new DefaultTableModel(employeeColumnNames, 0);
		JTable employeeTable = new JTable(employeeTableModel);
		employeeTable.setFont(new Font("Consolas", Font.PLAIN, 13));
		displayEmployeeTable(EmployeeBusiness.list);
		employeeScrollPane.setViewportView(employeeTable);
		
		btnDeleteEmployee = new JButton("Delete");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int[] selectedRows = employeeTable.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
				}
				else {
					for (int i = 0; i < selectedRows.length; i++) {
						int id = Integer.parseInt(employeeTable.getValueAt(selectedRows[i], 0).toString());
						if (id == currentUser.getEmployeeId()) {
							if (JOptionPane.showConfirmDialog(contentPane, "Are you sure to delete your own account?") == 0) {
								EmployeeBusiness.deleteEmployee(id);
								FrameLogIn frame = new FrameLogIn();
								frame.setVisible(true);
								getFrame().dispose();
							}
						}
						else EmployeeBusiness.deleteEmployee(id);
					}
					displayEmployeeTable(EmployeeBusiness.list);
				}
			}
		});
		btnDeleteEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnDeleteEmployee.setBounds(760, 435, 80, 30);
		employeePanel.add(btnDeleteEmployee);
		
		btnUpdateEmployee = new JButton("Update");
		btnUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int[] selectedRows = employeeTable.getSelectedRows();
				if (selectedRows.length != 1) {
					JOptionPane.showMessageDialog(contentPane, "Please select exactly one row to update!");
				}
				else {
					int id = Integer.parseInt(employeeTable.getValueAt(selectedRows[0], 0).toString());
					FrameUpdateEmployee frame = new FrameUpdateEmployee(getFrame());
					frame.displayAccountInformation(EmployeeBusiness.getEmployeeById(id));
					frame.setVisible(true);
				}
			}
		});
		btnUpdateEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnUpdateEmployee.setBounds(670, 435, 80, 30);
		employeePanel.add(btnUpdateEmployee);
		
		btnAddEmployee = new JButton("Add");
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameAddEmployee frame = new FrameAddEmployee(getFrame());
				frame.setVisible(true);
			}
		});
		btnAddEmployee.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnAddEmployee.setBounds(580, 435, 80, 30);
		employeePanel.add(btnAddEmployee);
		
		JButton btnCancel2 = new JButton("Cancel");
		btnCancel2.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel2.setBounds(850, 435, 80, 30);
		btnCancel2.addActionListener(new CancelActionListener());
		employeePanel.add(btnCancel2);
		
		// transaction panel
		JPanel transactionPanel = new JPanel();
		tabbedPane.addTab("Transaction Management", null, transactionPanel, null);
		transactionPanel.setLayout(null);
		
		JLabel lblSearchTransaction = new JLabel("Search:");
		lblSearchTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblSearchTransaction.setBounds(30, 24, 60, 30);
		transactionPanel.add(lblSearchTransaction);
		
		JTextField txtSearchTransaction = new JTextField();
		txtSearchTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtSearchTransaction.setBounds(100, 25, 250, 30);
		transactionPanel.add(txtSearchTransaction);
		
		JLabel lblFilterTransaction = new JLabel("Filter:");
		lblFilterTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblFilterTransaction.setBounds(397, 24, 60, 30);
		transactionPanel.add(lblFilterTransaction);
		
		JComboBox<String> cbbFilterTransaction = new JComboBox<String>();
		cbbFilterTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		cbbFilterTransaction.setModel(new DefaultComboBoxModel<String>(new String[] {"Transaction's ID", "Employee", "Product"}));
		cbbFilterTransaction.setBounds(467, 24, 113, 30);
		transactionPanel.add(cbbFilterTransaction);
		
		JButton btnSearchTransaction = new JButton("Search");
		btnSearchTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					String filter = cbbFilterTransaction.getSelectedItem().toString();
					String key = txtSearchTransaction.getText();
					ArrayList<Transaction> list = new ArrayList<Transaction>();
					for (Transaction e: TransactionBusiness.list) {
						if (filter.equals("Transaction's ID")) {
							if (Integer.toString(e.getTransactionId()).indexOf(key.trim().toLowerCase()) != -1) {
								list.add(e);
							}
						} 
						else if (filter.equals("Employee")) {
							if (Integer.toString(e.getEmployeeId()).toLowerCase().indexOf(key.trim().toLowerCase()) != -1 || EmployeeBusiness.getUsernameById(e.getEmployeeId()).toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
								list.add(e);
							}
						}
						else if (filter.equals("Product")) {
							if (Integer.toString(e.getProductId()).toLowerCase().indexOf(key.trim().toLowerCase()) != -1 || ProductBusiness.getNameById(e.getProductId()).toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
								list.add(e);
							}
						}
					}
					displayTransactionTable(list);
				
			}
		});
		btnSearchTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnSearchTransaction.setBounds(612, 24, 80, 30);
		transactionPanel.add(btnSearchTransaction);
		
		JButton btnRefreshTransactionTable = new JButton("Refresh");  
		btnRefreshTransactionTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayTransactionTable(TransactionBusiness.list);
				txtSearchTransaction.setText(null);
			}
		});
		btnRefreshTransactionTable.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshTransactionTable.setBounds(714, 24, 85, 30);
		transactionPanel.add(btnRefreshTransactionTable);
		
		JScrollPane transactionScrollPane = new JScrollPane();
		transactionScrollPane.setBounds(30, 80, 900, 330);
		transactionPanel.add(transactionScrollPane);
		
		String[] transactionColumnNames = {"Transaction's ID", "Date", "Employee's ID", "Employee", "Product's ID", "Product", "Quantity", "Bill"};
		transactionTableModel = new DefaultTableModel(transactionColumnNames, 0);
		JTable transactionTable = new JTable(transactionTableModel);
		transactionTable.setFont(new Font("Consolas", Font.PLAIN, 13));
		displayTransactionTable(TransactionBusiness.list);
		transactionScrollPane.setViewportView(transactionTable);
		
		JButton btnAddTransaction = new JButton("Add");
		btnAddTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameNewTransaction frame = new FrameNewTransaction(getFrame());
				frame.setVisible(true);
			}
		});
		btnAddTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnAddTransaction.setBounds(580, 435, 80, 30);
		transactionPanel.add(btnAddTransaction);
		
		JButton btnUpdateTransaction = new JButton("Update");
		btnUpdateTransaction.setEnabled(false);
		btnUpdateTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnUpdateTransaction.setBounds(670, 435, 80, 30);
		transactionPanel.add(btnUpdateTransaction);
		
		JButton btnDeleteTransaction = new JButton("Delete");
		btnDeleteTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int[] selectedRows = transactionTable.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
				}
				else {
					for (int i = 0; i < selectedRows.length; i++) {
						int id = Integer.parseInt(transactionTable.getValueAt(selectedRows[i], 0).toString());
						TransactionBusiness.deleteTransaction(id);
					}
					displayTransactionTable(TransactionBusiness.list);
					displayProductTable(ProductBusiness.list);
				}
			}
		});
		btnDeleteTransaction.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnDeleteTransaction.setBounds(760, 435, 80, 30);
		transactionPanel.add(btnDeleteTransaction);	
		
		JButton btnCancel3 = new JButton("Cancel");
		btnCancel3.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel3.setBounds(850, 435, 80, 30);
		btnCancel3.addActionListener(new CancelActionListener());
		transactionPanel.add(btnCancel3);
		
		// expense panel
		JPanel expensePanel = new JPanel();
		tabbedPane.addTab("Expense Management", null, expensePanel, null);
		expensePanel.setLayout(null);
		
		JLabel lblSearchExpense = new JLabel("Search:");
		lblSearchExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblSearchExpense.setBounds(30, 24, 60, 30);
		expensePanel.add(lblSearchExpense);
		
		JTextField txtSearchExpense = new JTextField();
		txtSearchExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		txtSearchExpense.setBounds(100, 25, 250, 30);
		expensePanel.add(txtSearchExpense);
		
		JLabel lblFilterExpense = new JLabel("Filter:");
		lblFilterExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblFilterExpense.setBounds(397, 24, 60, 30);
		expensePanel.add(lblFilterExpense);
		
		JComboBox<String> cbbFilterExpense = new JComboBox<String>();
		cbbFilterExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		cbbFilterExpense.setModel(new DefaultComboBoxModel<String>(new String[] {"Expense's ID", "Employee", "Content"}));
		cbbFilterExpense.setBounds(467, 24, 113, 30);
		expensePanel.add(cbbFilterExpense);
		
		JButton btnSearchExpense = new JButton("Search");
		btnSearchExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String filter = cbbFilterExpense.getSelectedItem().toString();
				String key = txtSearchExpense.getText();
				ArrayList<Expense> list = new ArrayList<Expense>();
				for (Expense e: ExpenseBusiness.list) {
					if (filter.equals("Expense's ID")) {
						if (Integer.toString(e.getExpenseId()).indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					} 
					else if (filter.equals("Employee")) {
						if (Integer.toString(e.getEmployee().getEmployeeId()).toLowerCase().indexOf(key.trim().toLowerCase()) != -1 || e.getEmployee().getUsername().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
					else if (filter.equals("Content")) {
						if (e.getContent().toLowerCase().indexOf(key.trim().toLowerCase()) != -1) {
							list.add(e);
						}
					}
				}
				displayExpenseTable(list);
			}
		});
		btnSearchExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnSearchExpense.setBounds(612, 24, 80, 30);
		expensePanel.add(btnSearchExpense);
		
		JButton btnRefreshExpenseTable = new JButton("Refresh");  
		btnRefreshExpenseTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayExpenseTable(ExpenseBusiness.list);
				txtSearchExpense.setText(null);
			}
		});
		btnRefreshExpenseTable.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnRefreshExpenseTable.setBounds(714, 24, 85, 30);
		expensePanel.add(btnRefreshExpenseTable);
		
		JScrollPane expenseScrollPane = new JScrollPane();
		expenseScrollPane.setBounds(30, 80, 900, 330);
		expensePanel.add(expenseScrollPane);
		
		String[] expenseColumnNames = {"Expense's ID", "Date", "Employee's ID", "Employee", "Receiver's ID", "Receiver", "Content", "Bill"};
		expenseTableModel = new DefaultTableModel(expenseColumnNames, 0);
		JTable expenseTable = new JTable(expenseTableModel);
		expenseTable.setFont(new Font("Consolas", Font.PLAIN, 13));
		displayExpenseTable(ExpenseBusiness.list);
		expenseScrollPane.setViewportView(expenseTable);
		
		JButton btnAddExpense = new JButton("Add");
		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameNewExpense frame = new FrameNewExpense(getFrame());
				frame.setVisible(true);
			}
		});
		btnAddExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnAddExpense.setBounds(580, 435, 80, 30);
		expensePanel.add(btnAddExpense);
		
		JButton btnUpdateExpense = new JButton("Update");
		btnUpdateExpense.setEnabled(false);
		btnUpdateExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnUpdateExpense.setBounds(670, 435, 80, 30);
		expensePanel.add(btnUpdateExpense);
		
		JButton btnDeleteExpense = new JButton("Delete");
		btnDeleteExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int[] selectedRows = expenseTable.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(contentPane, "Please select rows to delete!");
				}
				else {
					for (int i = 0; i < selectedRows.length; i++) {
						int id = Integer.parseInt(expenseTable.getValueAt(selectedRows[i], 0).toString());
						ExpenseBusiness.deleteExpense(id);
					}
					displayExpenseTable(ExpenseBusiness.list);
				}
			}
		});
		btnDeleteExpense.setFont(new Font("Consolas", Font.PLAIN, 12));
		btnDeleteExpense.setBounds(760, 435, 80, 30);
		expensePanel.add(btnDeleteExpense);	
		
		JButton btnCancel4 = new JButton("Cancel");
		btnCancel4.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel4.setBounds(850, 435, 80, 30);
		btnCancel4.addActionListener(new CancelActionListener());
		expensePanel.add(btnCancel4);
	}
	
	public MainFrame getFrame() {
		return this;
	}
	
	public void setCurrentUser(Employee e) {
		this.currentUser = e;
	}
	
	private void removeAllRows(DefaultTableModel d) {
		for (int i = d.getRowCount() - 1; i >= 0; i--) {
			d.removeRow(i);
		}
	}
	
	public void displayInformation() {
		txtId.setText(Integer.toString(currentUser.getEmployeeId()));
		txtName.setText(currentUser.getName());
		txtDob.setText("" + currentUser.getDob());
		txtPhone.setText(currentUser.getPhone());
		txtUsername.setText(currentUser.getUsername());
		txtType.setText(currentUser.getAccountType());
	}
	
	public void displayProductTable(ArrayList<ProductGroup> list) {
		removeAllRows(productTableModel);
		for (ProductGroup pg: list) {
			String type = pg.getProduct().getClass().getSimpleName();			
			int productId = pg.getProduct().getProductId();
			String name = pg.getProduct().getName();
			String color = pg.getProduct().getColor();
			int price = pg.getProduct().getPrice();
			int quantity = pg.getQuantity();
			if (type.equals("Clothes")) {
				Clothes c = (Clothes) pg.getProduct();
				String size = c.getSize();
				Object[] obj = {type, productId, name, color, size, null, price, quantity};
				productTableModel.addRow(obj);
			}
			else if (type.equals("Shoes")) {
				Shoes s = (Shoes) pg.getProduct();
				int size = s.getSize();
				Object[] obj = {type, productId, name, color, size, null, price, quantity};
				productTableModel.addRow(obj);
			}
			else if (type.equals("Bag")) {
				Bag b = (Bag) pg.getProduct();
				String size = b.getStringSize(b.getHeight(), b.getWidth(), b.getDepth());
				Object[] obj = {type, productId, name, color, size, null, price, quantity};
				productTableModel.addRow(obj);
			}
			else if (type.equals("Jewellery")) {
				Jewellery j = (Jewellery) pg.getProduct();
				String material = j.getMaterial();
				Object[] obj = {type, productId, name, color, null, material, price, quantity};
				productTableModel.addRow(obj);
			}
			
		}
	}
	
	public void displayEmployeeTable(ArrayList<Employee> list) {
		removeAllRows(employeeTableModel);
		for (Employee e: list) {
			int employeeId = e.getEmployeeId();
			String name = e.getName();
			LocalDate dob = e.getDob();
			String phone = e.getPhone();
			String userName = e.getUsername();
			String type = e.getAccountType();
			Object[] obj = {employeeId, name, dob, phone, userName, type};
			employeeTableModel.addRow(obj);
		}
	}
	
	public void displayTransactionTable(ArrayList<Transaction> list) {
		removeAllRows(transactionTableModel);
		for (Transaction t: list) {
			int id = t.getTransactionId();
			Date date = t.getDate();
			int e = t.getEmployeeId();
			int p = t.getProductId();
			int quantity = t.getQuantity();
			int bill = t.getBill();
			
			Object[] obj = {id, date, e, EmployeeBusiness.getUsernameById(e), p, ProductBusiness.getNameById(p), quantity, bill};
			transactionTableModel.addRow(obj);
		}
	}
	
	public void displayExpenseTable(ArrayList<Expense> list) {
		removeAllRows(expenseTableModel);
		for (Expense e: list) {
			int id = e.getExpenseId();
			LocalDate date = e.getDate();
			Employee em = e.getEmployee();
			String content = e.getContent();
			int bill = e.getBill();
			if (e instanceof Salary) {
				Employee receiver = ((Salary) e).getReceiver();
				Object[] obj = {id, date, em.getEmployeeId(), em.getUsername(), receiver.getEmployeeId(), receiver.getUsername(), content, bill};
				expenseTableModel.addRow(obj);
			}
			else {
				Object[] obj = {id, date, em.getEmployeeId(), em.getUsername(), null, null, content, bill};
				expenseTableModel.addRow(obj);
			}
		}
	}
	
	public class CancelActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit") == 0) {
				getFrame().dispose();
			}
		}
	}
	
	protected void validateButtons() {
		if (currentUser.getAccountType().equals("Administrator")) {
			btnDeleteEmployee.setEnabled(true);
			btnAddEmployee.setEnabled(true);
			btnUpdateEmployee.setEnabled(true);
		}
		else {
			btnDeleteEmployee.setEnabled(false);
			btnAddEmployee.setEnabled(false);
			btnUpdateEmployee.setEnabled(false);
		}
	}
}
