package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import Controller.TransactionBusiness;

public class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int transactionId;
	private Date date;
	private int employeeId;
	private int productId;
	private int quantity;
	private int bill;
	
	public static int getNextId() {
		int id = 1;
		ArrayList<Transaction> list = TransactionBusiness.list;
		if (list.size() > 0) {
			return list.get(list.size() - 1).getTransactionId() + 1; 
		}
		return id;
	}
	
	public Transaction(Date date, int employeeId, int productId, int quantity, int bill) {
		this.transactionId = getNextId();
		this.date = date;
		this.employeeId = employeeId;
		this.productId = productId;
		this.quantity = quantity;
		this.bill = bill;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}
}
