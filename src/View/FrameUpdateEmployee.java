package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

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

import com.toedter.calendar.JDateChooser;

import Controller.EmployeeBusiness;
import Model.Employee;

public class FrameUpdateEmployee extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel txtId;
	private JLabel txtName;
	private JDateChooser dateChooser;
	private JTextField txtPhoneNumber;
	private JLabel txtUserName;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JComboBox<String> cbbType;
	

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrameUpdateEmployee frame = new FrameUpdateEmployee();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public FrameUpdateEmployee(MainFrame mainFrame) {
		setTitle("Update account information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		Font normalFont = new Font("Consolas", Font.PLAIN, 13);
		Font boldFont = new Font("Consolas", Font.BOLD, 14);
		
		JLabel lblId = new JLabel("Employee's ID:");
		lblId.setFont(normalFont);
		lblId.setBounds(23, 35, 100, 14);
		panel.add(lblId);
		
		txtId = new JLabel("");
		txtId.setFont(boldFont);
		txtId.setBounds(160, 35, 100, 14);
		panel.add(txtId);
		
		JLabel lblName = new JLabel("Full name:");
		lblName.setFont(normalFont);
		lblName.setBounds(23, 80, 100, 14);
		panel.add(lblName);
		
		txtName = new JLabel();
		txtName.setFont(boldFont);
		txtName.setBounds(160, 80, 100, 14);
		panel.add(txtName);
		
		JLabel lblDob = new JLabel("Date of birth:");
		lblDob.setFont(normalFont);
		lblDob.setBounds(23, 125, 100, 14);
		panel.add(lblDob);
		
		dateChooser = new JDateChooser();
		dateChooser.getSpinner().setFont(normalFont);
		dateChooser.setBounds(160, 115, 270, 30);
		panel.add(dateChooser);
		
		JLabel lblPhone = new JLabel("Phone number:");
		lblPhone.setFont(normalFont);
		lblPhone.setBounds(23, 170, 100, 14);
		panel.add(lblPhone);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setFont(normalFont);
		txtPhoneNumber.setBounds(160, 160, 270, 30);
		panel.add(txtPhoneNumber);
		txtPhoneNumber.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(normalFont);
		lblUsername.setBounds(23, 215, 67, 14);
		panel.add(lblUsername);
		
		txtUserName = new JLabel();
		txtUserName.setFont(boldFont);
		txtUserName.setBounds(160, 215, 100, 14);
		panel.add(txtUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(normalFont);
		lblPassword.setBounds(23, 260, 88, 14);
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(normalFont);
		txtPassword.setBounds(160, 250, 270, 30);
		panel.add(txtPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setFont(normalFont);
		lblConfirmPassword.setBounds(23, 305, 119, 14);
		panel.add(lblConfirmPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(normalFont);
		txtConfirmPassword.setBounds(160, 295, 270, 30);
		panel.add(txtConfirmPassword);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(normalFont);
		lblType.setBounds(23, 350, 46, 14);
		panel.add(lblType);
		
		cbbType = new JComboBox<String>();
		cbbType.setFont(normalFont);
		cbbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Standard", "Administrator"}));
		cbbType.setBounds(160, 340, 270, 30);
		panel.add(cbbType);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int id = Integer.parseInt(txtId.getText());
				LocalDate dob = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String phone = txtPhoneNumber.getText();
				char[] password = txtPassword.getPassword();
				char[] confirmPassword = txtConfirmPassword.getPassword();
				String type = cbbType.getSelectedItem().toString();
				
				if (Arrays.equals(password, confirmPassword)) {
					EmployeeBusiness.updateEmployee(id, dob, phone, password, type);
					mainFrame.displayEmployeeTable(EmployeeBusiness.list);
					getFrame().dispose();
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Confirm password is wrong. Please retype!");
				}
			}
		});
		btnUpdate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnUpdate.setBounds(231, 387, 88, 30);
		panel.add(btnUpdate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				getFrame().dispose();
			}
		});
		btnCancel.setBounds(342, 387, 88, 30);
		panel.add(btnCancel);
	}
	
	public FrameUpdateEmployee getFrame() {
		return this;
	}
	
	public void displayAccountInformation(Employee e) {
		txtId.setText(Integer.toString(e.getEmployeeId()));
		txtName.setText(e.getName());
		dateChooser.setDate(Date.from(e.getDob().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		txtPhoneNumber.setText(e.getPhone());
		txtUserName.setText(e.getUsername());
		txtPassword.setText(new String(e.getPassword()));
		txtConfirmPassword.setText(new String(e.getPassword()));
		cbbType.setSelectedItem(e.getAccountType());
	}
}
