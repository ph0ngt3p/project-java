package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.ProductBusiness;
import Controller.TransactionBusiness;
import Model.Product;
import Model.Transaction;

public class FrameNewTransaction extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	public JTextField txtProductId;

	public FrameNewTransaction(MainFrame mainFrame) {
		setTitle("New Transaction");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Transaction Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 452, 354);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Font normalFont = new Font("Consolas", Font.PLAIN, 13);
		Font boldFont = new Font("Consolas", Font.BOLD, 14);
		
		JLabel lblId = new JLabel("Transaction's ID:");
		lblId.setFont(normalFont);
		lblId.setBounds(23, 35, 119, 30);
		panel.add(lblId);
		
		JLabel txtId = new JLabel("");
		txtId.setFont(boldFont);
		txtId.setText("" + Transaction.getNextId());
		txtId.setBounds(160, 35, 100, 30);
		panel.add(txtId);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(normalFont);
		lblDate.setBounds(23, 80, 100, 30);
		panel.add(lblDate);
		
		JLabel txtDate = new JLabel();
		txtDate.setFont(boldFont);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		txtDate.setText(df.format(new Date()));
		txtDate.setBounds(160, 80, 270, 30);
		panel.add(txtDate);
		
		JLabel lblEmployeeId = new JLabel("Employee's ID:");
		lblEmployeeId.setFont(normalFont);
		lblEmployeeId.setBounds(23, 125, 100, 30);
		panel.add(lblEmployeeId);
		
		JLabel txtEmployeeId = new JLabel();
		txtEmployeeId.setFont(boldFont);
		txtEmployeeId.setText("" + mainFrame.currentUser.getEmployeeId());
		txtEmployeeId.setBounds(160, 125, 270, 30);
		panel.add(txtEmployeeId);
		
		JLabel lblProductId = new JLabel("Product's ID:");
		lblProductId.setFont(normalFont);
		lblProductId.setBounds(23, 170, 100, 30);
		panel.add(lblProductId);
		
		txtProductId = new JTextField();
		txtProductId.setFont(normalFont);
		txtProductId.setBounds(160, 165, 270, 30);
		panel.add(txtProductId);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(normalFont);
		lblQuantity.setBounds(23, 215, 88, 30);
		panel.add(lblQuantity);
		
		JTextField txtQuantity = new JTextField();
		txtQuantity.setFont(normalFont);
		txtQuantity.setBounds(160, 210, 270, 30);
		panel.add(txtQuantity);
		
		JLabel lblBill = new JLabel("Bill:");
		lblBill.setFont(normalFont);
		lblBill.setBounds(23, 260, 119, 30);
		panel.add(lblBill);
		
		JLabel txtBill = new JLabel("0");
		txtBill.setFont(boldFont);
		txtBill.setBounds(160, 260, 270, 30);
		panel.add(txtBill);
		
		DocumentListener billListener = new DocumentListener() {
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
				try {
					int price = Product.getPriceById(Integer.parseInt(txtProductId.getText()));
					int quantity = Integer.parseInt(txtQuantity.getText());
					txtBill.setText("" + (price * quantity));
				} catch (NumberFormatException e) {
					txtBill.setText("0");
				}
			}
		};
		txtProductId.getDocument().addDocumentListener(billListener);
		txtQuantity.getDocument().addDocumentListener(billListener);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					Date date = df.parse(txtDate.getText());
					int employeeId = mainFrame.currentUser.getEmployeeId();
					int productId = Integer.parseInt(txtProductId.getText());
					int quantity = Integer.parseInt(txtQuantity.getText());
					int bill = Integer.parseInt(txtBill.getText());
					int check = TransactionBusiness.addTransaction(new Transaction(date, employeeId, productId, quantity, bill));
					if (check == -1) {
						JOptionPane.showMessageDialog(contentPane, "This product does not exist!");
					}
					else if (check == 0) {
						JOptionPane.showMessageDialog(contentPane, "Not enough products left!");
					}
					else {
						mainFrame.displayTransactionTable(TransactionBusiness.list);
						mainFrame.displayProductTable(ProductBusiness.list);
						getFrame().dispose();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		btnCreate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCreate.setBounds(244, 305, 88, 30);
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
		btnCancel.setBounds(342, 305, 88, 30);
		panel.add(btnCancel);
	}
	
	public FrameNewTransaction getFrame() {
		return this;
	}
}
