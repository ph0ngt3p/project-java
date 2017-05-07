package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.ProductBusiness;
import Model.Bag;
import Model.Clothes;
import Model.Jewellery;
import Model.ProductGroup;
import Model.Shoes;

public class FrameUpdateProduct extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel txtProductId;
	private JTextField txtName;
	private JTextField txtColor;
	private JTextField txtPrice;
	private JTextField txtSize;
	private JTextField txtMaterial;
	private JComboBox<String> cbbType;
	private JButton btnUpdate;
	private JTextField txtQuantity;

	public FrameUpdateProduct(MainFrame mainFrame) {
		setTitle("Update products");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 451, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblType.setBounds(35, 35, 82, 20);
		contentPane.add(lblType);
		
		cbbType = new JComboBox<String>();
		cbbType.setFont(new Font("Consolas", Font.PLAIN, 13));
		cbbType.setModel(new DefaultComboBoxModel<String>(new String[] {"Clothes", "Shoes", "Bag", "Jewellery"}));
		cbbType.addItemListener(new TypeItemListener());
		cbbType.setEnabled(false);
		cbbType.setBounds(140, 30, 250, 30);
		contentPane.add(cbbType);
		
		JLabel lblId = new JLabel("Product's ID:");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(35, 80, 95, 20);
		contentPane.add(lblId);
		
		txtProductId = new JLabel();
		txtProductId.setFont(new Font("Consolas", Font.BOLD, 13));
		txtProductId.setBounds(140, 75, 250, 30);
		contentPane.add(txtProductId);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblName.setBounds(35, 125, 82, 20);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtName.getDocument().addDocumentListener(new InputDocumentListener());
		txtName.setBounds(140, 120, 250, 30);
		contentPane.add(txtName);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblColor.setBounds(35, 170, 82, 20);
		contentPane.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtColor.setBounds(140, 165, 250, 30);
		contentPane.add(txtColor);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPrice.setBounds(35, 215, 82, 20);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtPrice.getDocument().addDocumentListener(new InputDocumentListener());
		txtPrice.setBounds(140, 210, 250, 30);
		contentPane.add(txtPrice);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblSize.setBounds(35, 260, 82, 20);
		contentPane.add(lblSize);
		
		txtSize = new JTextField();
		txtSize.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtSize.setBounds(140, 255, 250, 30);
		contentPane.add(txtSize);
		
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblMaterial.setBounds(35, 305, 82, 20);
		contentPane.add(lblMaterial);
		
		txtMaterial = new JTextField();
		txtMaterial.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtMaterial.setEnabled(false);
		txtMaterial.setBounds(140, 300, 250, 30);
		contentPane.add(txtMaterial);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblQuantity.setBounds(35, 350, 82, 20);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtQuantity.getDocument().addDocumentListener(new InputDocumentListener());
		txtQuantity.setBounds(140, 345, 250, 30);
		contentPane.add(txtQuantity);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel.addActionListener(new CancelActionListener());
		btnCancel.setBounds(308, 395, 82, 30);
		contentPane.add(btnCancel);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					int id = Integer.parseInt(txtProductId.getText().trim());
					String type = cbbType.getSelectedItem().toString().trim();
					String name = txtName.getText().trim();
					String color = txtColor.getText().trim();
					int price = Integer.parseInt(txtPrice.getText().trim());
					String strSize = txtSize.getText().trim();
					String material = txtMaterial.getText().trim();
					int quantity = Integer.parseInt(txtQuantity.getText().trim());
					ProductBusiness.updateProduct(id, type, name, color, price, strSize, material, quantity);
					mainFrame.displayProductTable(ProductBusiness.list);
					getFrame().dispose();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(contentPane, "Price and quantity must be numbers!");
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(239, 395, 59, 30);
		contentPane.add(btnUpdate);	
	}

	public FrameUpdateProduct getFrame() {
		return this;
	}
	
	public class TypeItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			String selectedType = (String) event.getItem();
			if (selectedType.equals("Clothes") || selectedType.equals("Shoes") || selectedType.equals("Bag")) {
				txtMaterial.setEnabled(false);
				txtSize.setEnabled(true);
			}
			else {
				txtMaterial.setEnabled(true);
				txtSize.setEnabled(false);
			}
		}
	}
	
	public class InputDocumentListener implements DocumentListener {
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
			if (txtName.getText().equals("") || txtPrice.getText().equals("") || txtQuantity.getText().equals(""))
				btnUpdate.setEnabled(false);
			else 
				btnUpdate.setEnabled(true);
		}
	}
	
	public class CancelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit") == 0) {
				getFrame().dispose();
			}
		}
	}
	
	protected void displayInformation(ProductGroup pg) {
		String type = pg.getProduct().getClass().getSimpleName();
		cbbType.setSelectedItem(type);
		txtProductId.setText("" + pg.getProduct().getProductId());
		txtName.setText(pg.getProduct().getName());
		txtColor.setText(pg.getProduct().getColor());
		txtPrice.setText("" + pg.getProduct().getPrice());
		txtQuantity.setText("" + pg.getQuantity());
		if (type.equals("Clothes")) {
			txtSize.setText(((Clothes) pg.getProduct()).getSize());
		}
		else if (type.equals("Shoes")) {
			txtSize.setText("" + ((Shoes) pg.getProduct()).getSize());
		}
		else if (type.equals("Bag")) {
			Bag b = (Bag) pg.getProduct();
			txtSize.setText(b.getStringSize(b.getHeight(), b.getWidth(), b.getDepth()));
		}
		else if (type.equals("Jewellery")) {
			txtMaterial.setText(((Jewellery) pg.getProduct()).getMaterial());
		}
	}
}
