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
import Model.Product;
import Model.Shoes;

public class FrameAddProduct extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel txtProductId;
	private JTextField txtName;
	private JTextField txtColor;
	private JTextField txtPrice;
	private JTextField txtSize;
	private JTextField txtMaterial;
	private JComboBox<String> cbbType;
	private JButton btnAdd;
	private JTextField txtQuantity;

	public FrameAddProduct(MainFrame mainFrame) {
		setTitle("Add new products");
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
		cbbType.setBounds(140, 30, 250, 30);
		contentPane.add(cbbType);
		
		JLabel lblId = new JLabel("Product's ID:");
		lblId.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblId.setBounds(35, 80, 95, 20);
		contentPane.add(lblId);
		
		txtProductId = new JLabel();
		txtProductId.setFont(new Font("Consolas", Font.BOLD, 13));
		txtProductId.setBounds(140, 75, 250, 30);
		txtProductId.setText(Integer.toString(Product.getNextId()));
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
		txtName.setColumns(10);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblColor.setBounds(35, 170, 82, 20);
		contentPane.add(lblColor);
		
		txtColor = new JTextField();
		txtColor.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtColor.setBounds(140, 165, 250, 30);
		contentPane.add(txtColor);
		txtColor.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblPrice.setBounds(35, 215, 82, 20);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtPrice.getDocument().addDocumentListener(new InputDocumentListener());
		txtPrice.setBounds(140, 210, 250, 30);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblSize.setBounds(35, 260, 82, 20);
		contentPane.add(lblSize);
		
		txtSize = new JTextField();
		txtSize.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtSize.setBounds(140, 255, 250, 30);
		contentPane.add(txtSize);
		txtSize.setColumns(10);
		
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblMaterial.setBounds(35, 305, 82, 20);
		contentPane.add(lblMaterial);
		
		txtMaterial = new JTextField();
		txtMaterial.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtMaterial.setEnabled(false);
		txtMaterial.setBounds(140, 300, 250, 30);
		contentPane.add(txtMaterial);
		txtMaterial.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Consolas", Font.PLAIN, 13));
		lblQuantity.setBounds(35, 350, 82, 20);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Consolas", Font.PLAIN, 13));
		txtQuantity.getDocument().addDocumentListener(new InputDocumentListener());
		txtQuantity.setBounds(140, 345, 250, 30);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnCancel.addActionListener(new CancelActionListener());
		btnCancel.setBounds(308, 395, 82, 30);
		contentPane.add(btnCancel);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Consolas", Font.PLAIN, 13));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					String type = cbbType.getSelectedItem().toString();
					String name = txtName.getText();
					String color = txtColor.getText();
					int price = Integer.parseInt(txtPrice.getText());
					String strSize = txtSize.getText();
					String material = txtMaterial.getText();
					int quantity = Integer.parseInt(txtQuantity.getText());
				
					if (type.equals("Clothes")) {
						Clothes c = new Clothes(name, color, price, strSize);
						ProductBusiness.addProduct(c, quantity);
					}
					else if (type.equals("Shoes")) {
						try {
							Shoes s = new Shoes(name, color, price, Integer.parseInt(strSize));
							ProductBusiness.addProduct(s, quantity);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(contentPane, "Shoe size must be a number!");
						}
					}
					else if (type.equals("Bag")) {
						try {
							Bag b = new Bag(name, color, price, Bag.getArraySize(strSize));
							ProductBusiness.addProduct(b, quantity);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(contentPane, "Bag size must be in form of height x width x depth!");
						}
					}
					else if (type.equals("Jewellery")) {
						Jewellery j = new Jewellery(name, color, price, material);
						ProductBusiness.addProduct(j, quantity);
					}
					mainFrame.displayProductTable(ProductBusiness.list);
					getFrame().dispose();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(contentPane, "Price and quantity must be numbers!");
				}
			}
		});
		btnAdd.setEnabled(false);
		btnAdd.setBounds(239, 395, 59, 30);
		contentPane.add(btnAdd);	
	}

	public FrameAddProduct getFrame() {
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
				btnAdd.setEnabled(false);
			else 
				btnAdd.setEnabled(true);
		}
	}
	
	public class CancelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			getFrame().dispose();
		}
	}
}
