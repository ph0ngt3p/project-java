package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Controller.ExpenseBusiness;

public class Expense implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int expenseId;
	private LocalDate date;
	private Employee employee;
	private String content;
	private int bill;
	
	public static int getNextId() {
		int id = 1;
		ArrayList<Expense> list = ExpenseBusiness.list;
		if (list.size() > 0) {
			id = list.get(list.size() - 1).getExpenseId() + 1;
		}
		return id;
	}
	
	public Expense() {
		
	}
	
	public Expense (LocalDate date, Employee e, String content, int bill) {
		this.expenseId = getNextId();
		this.date = date;
		this.employee = e;
		this.content = content;
		this.bill = bill;
	}
	
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
}
