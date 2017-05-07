package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import Controller.EmployeeBusiness;
import Controller.ExpenseBusiness;
import Model.Expense;
import Model.Salary;

public class FrameNewExpense extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JComboBox<String> cbbType;
	private JTextField txtReceiver;

	public FrameNewExpense(MainFrame mainFrame) {
		setTitle("New expense");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Expense Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 452, 401);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblType.setBounds(25, 45, 46, 20);
		panel.add(lblType);
		
		cbbType = new JComboBox<String>();
		cbbType.setFont(new Font("Consolas", Font.PLAIN, 13));
		cbbType.addItemListener(new TypeItemListener());
		cbbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Salary", "Others"}));
		cbbType.setBounds(160, 40, 270, 30);
		panel.add(cbbType);
		
		JLabel lblId = new JLabel("Expense's ID:");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(25, 90, 100, 20);
		panel.add(lblId);
		
		JLabel txtId = new JLabel("");
		txtId.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtId.setText(Integer.toString(Expense.getNextId()));
		txtId.setBounds(160, 90, 100, 14);
		panel.add(txtId);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblDate.setBounds(25, 135, 100, 20);
		panel.add(lblDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getSpinner().setFont(new Font("Consolas", Font.PLAIN, 13));
		dateChooser.setBounds(160, 125, 270, 30);
		panel.add(dateChooser);
		
		JLabel lblEmployee = new JLabel("Employee's ID:");
		lblEmployee.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblEmployee.setBounds(25, 180, 100, 20);
		panel.add(lblEmployee);
		
		JLabel txtEmployee = new JLabel();
		txtEmployee.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtEmployee.setText(Integer.toString(mainFrame.currentUser.getEmployeeId()));
		txtEmployee.setBounds(160, 170, 270, 30);
		panel.add(txtEmployee);
		
		JLabel lblReceiver = new JLabel("Receiver's ID:");
		lblReceiver.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblReceiver.setBounds(25, 225, 100, 20);
		panel.add(lblReceiver);
		
		txtReceiver = new JTextField();
		txtReceiver.setBounds(160, 215, 270, 30);
		panel.add(txtReceiver);
		
		JLabel lblContent = new JLabel("Content:");
		lblContent.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblContent.setBounds(25, 270, 67, 20);
		panel.add(lblContent);
		
		JTextArea txtContent = new JTextArea();
		txtContent.setBounds(160, 260, 270, 30);
		panel.add(txtContent);
		
		
		
		JLabel lblBill = new JLabel("Bill:");
		lblBill.setFont(new Font("Consolas", Font.PLAIN, 12));
		lblBill.setBounds(25, 315, 46, 20);
		panel.add(lblBill);
		
		JTextField txtBill = new JTextField();
		txtBill.setBounds(160, 305, 270, 30);
		panel.add(txtBill);
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String type = cbbType.getSelectedItem().toString();
					LocalDate date = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int employeeId = Integer.parseInt(txtEmployee.getText());
					String content = txtContent.getText();
					int bill = Integer.parseInt(txtBill.getText());
				
					if (type.equals("Others")) {
						Expense e = new Expense(date, EmployeeBusiness.getEmployeeById(employeeId), content, bill);
						ExpenseBusiness.addExpense(e);
						mainFrame.displayExpenseTable(ExpenseBusiness.list);
						getFrame().dispose();
					}
					else {
						try {
							int receiver = Integer.parseInt(txtReceiver.getText());
							if (EmployeeBusiness.getEmployeeById(receiver) != null) { 
								Salary s = new Salary(date, EmployeeBusiness.getEmployeeById(employeeId), EmployeeBusiness.getEmployeeById(receiver), content, bill);
								ExpenseBusiness.addExpense(s);
								mainFrame.displayExpenseTable(ExpenseBusiness.list);
								getFrame().dispose();
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "This ID does not exist!");
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(contentPane, "Receiver's ID is invalid!");
						}
					}
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(contentPane, "Please insert right format!");
				}
			}
		});
		btnCreate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCreate.setBounds(244, 353, 88, 30);
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
		btnCancel.setBounds(342, 353, 88, 30);
		panel.add(btnCancel);	
	}

	public FrameNewExpense getFrame() {
		return this;
	}
	
	public class TypeItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			String selectedType = (String) event.getItem();
			if (selectedType.equals("Others")) {
				txtReceiver.setEnabled(false);
			}
			else {
				txtReceiver.setEnabled(true);
			}
		}
	}
}
