package Model;

import java.time.LocalDate;

public class Salary extends Expense {
	
	private static final long serialVersionUID = 1L;
	
	private Employee receiver;
	
	public Salary (LocalDate date, Employee e, Employee receiver, String content, int bill) {
		super();
		this.setExpenseId(getNextId());
		this.setDate(date);
		this.setEmployee(e);
		this.receiver = receiver;
		this.setContent(content);
		this.setBill(bill);
	}

	public Employee getReceiver() {
		return receiver;
	}

	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}
}
