package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import Controller.EmployeeBusiness;
import Model.Employee;

public class FrameAddEmployee extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public FrameAddEmployee(MainFrame mainFrame) {
		setTitle("Create new account");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 452, 429);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("Employee's ID:");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		JLabel txtId = new JLabel("");
		txtId.setFont(new Font("Consolas", Font.BOLD, 12));
		txtId.setBounds(160, 35, 100, 14);
		txtId.setText(Integer.toString(Employee.getNextId()));
		panel.add(txtId);
		
		JLabel lblName = new JLabel("Full name:");
		lblName.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblName.setBounds(23, 80, 100, 14);
		panel.add(lblName);
		
		JTextField txtName = new JTextField();
		txtName.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtName.setBounds(160, 70, 270, 30);
		panel.add(txtName);
		
		JLabel lblDob = new JLabel("Date of birth:");
		lblDob.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblDob.setBounds(23, 125, 100, 14);
		panel.add(lblDob);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getSpinner().setFont(new Font("Consolas", Font.PLAIN, 13));
		dateChooser.setBounds(160, 115, 270, 30);
		panel.add(dateChooser);
		
		JLabel lblPhone = new JLabel("Phone number:");
		lblPhone.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPhone.setBounds(23, 170, 100, 14);
		panel.add(lblPhone);
		
		JTextField txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtPhoneNumber.setBounds(160, 160, 270, 30);
		panel.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblUsername.setBounds(23, 215, 67, 14);
		panel.add(lblUsername);
		
		JTextField txtUserName = new JTextField();
		txtUserName.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtUserName.setBounds(160, 205, 270, 30);
		panel.add(txtUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPassword.setBounds(23, 260, 88, 14);
		panel.add(lblPassword);
		
		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtPassword.setBounds(160, 250, 270, 30);
		panel.add(txtPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(23, 305, 119, 14);
		panel.add(lblConfirmPassword);
		
		JPasswordField txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtConfirmPassword.setBounds(160, 295, 270, 30);
		panel.add(txtConfirmPassword);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblType.setBounds(23, 350, 46, 14);
		panel.add(lblType);
		
		JComboBox<String> cbbType = new JComboBox<String>();
		cbbType.setFont(new Font("Consolas", Font.PLAIN, 13));
		cbbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Standard", "Administrator"}));
		cbbType.setBounds(160, 340, 270, 30);
		panel.add(cbbType);
		
		JButton btnCreate = new JButton("Create");
		getRootPane().setDefaultButton(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				String name = txtName.getText();
				LocalDate dob = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String phone = txtPhoneNumber.getText();
				String username = txtUserName.getText();
				char[] password = txtPassword.getPassword();
				char[] confirmPassword = txtConfirmPassword.getPassword();
				String type = cbbType.getSelectedItem().toString();
				
				if (username.indexOf(" ") != -1) {
					JOptionPane.showMessageDialog(contentPane, "Username is not valid!");
				}
				else {
					if (password.length <= 5) {
						JOptionPane.showMessageDialog(contentPane, "Password has to contain at least 6 characters!");
					}
					else if (!Arrays.equals(password, confirmPassword)) {
						JOptionPane.showMessageDialog(contentPane, "Confirm password is wrong!");
					}
					else {
						if (!isNumeric(phone)) {
							JOptionPane.showMessageDialog(contentPane, "Phone number is invalid!");
						}
						else {
							if (!EmployeeBusiness.addEmployee(name, dob, phone, username, password, type)) {
								JOptionPane.showMessageDialog(contentPane, "This username has already existed!");
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Create successfully!");
								if (mainFrame != null) {
									mainFrame.displayEmployeeTable(EmployeeBusiness.list);
								}
								getFrame().dispose();
							}
						}
					}
				}
			}
		});
		btnCreate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCreate.setBounds(231, 387, 88, 30);
		panel.add(btnCreate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit") == 0) {
					getFrame().dispose();
				}
			}
		});
		btnCancel.setBounds(342, 387, 88, 30);
		panel.add(btnCancel);
		DocumentListener documentListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent event) {
				change();
			}
			
			public void insertUpdate(DocumentEvent event) {
				change();
			}
			
			public void removeUpdate(DocumentEvent event) {
				change();
			}
			
			public void change() {
				if (!txtName.getText().equals("") && (txtPassword.getPassword().length != 0) && !txtUserName.getText().equals(""))
					btnCreate.setEnabled(true);
				else btnCreate.setEnabled(false);
			}
		};
		txtName.getDocument().addDocumentListener(documentListener);
		txtUserName.getDocument().addDocumentListener(documentListener);
		txtPassword.getDocument().addDocumentListener(documentListener);
	}

	public FrameAddEmployee getFrame() {
		return this;
	}
	
	private boolean isNumeric(String string) {
		boolean valid = true;
		for (int i = 0; i < string.length(); i++) {
			if (((string.charAt(i) < 48) || (string.charAt(i) > 57)) && (string.charAt(i) != 32)) {
				System.out.println("false");
				return false;
			}
		}
		return valid;
	}
}
