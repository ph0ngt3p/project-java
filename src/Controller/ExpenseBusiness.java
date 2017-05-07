package Controller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import Model.Expense;
import Model.Salary;
import Service.FileProcessor;

public class ExpenseBusiness {
	public static ArrayList<Expense> list = FileProcessor.getExpenseListFromFile();
	
	public static void addExpense(Expense e) {
		list.add(e);
		FileProcessor.writeExpenseListIntoFile(list);
	}
	
	public static void deleteExpense(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getExpenseId() == id) {
				list.remove(i);
				break;
			}
		}
		FileProcessor.writeExpenseListIntoFile(list);
	}
	
	public static int calculateSalary(Date begin, Date finish) {
		int salary = 0;
		for (Expense e: list) {
			if (e instanceof Salary) {
				Date b = Date.from(e.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				if (b.compareTo(begin) >= 0 && b.compareTo(finish) <= 0) {
					salary = salary + e.getBill();
				}
			}
		}
		return salary;
	}
	
	public static int calculateOtherExpenses(Date begin, Date finish) {
		int salary = 0;
		for (Expense e: list) {
			if (!(e instanceof Salary)) {
				Date b = Date.from(e.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				if (b.compareTo(begin) >= 0 && b.compareTo(finish) <= 0) {
					salary = salary + e.getBill();
				}
			}
		}
		return salary;
	}
	
	public static int calculateOutcome(Date begin, Date finish) {
		return calculateSalary(begin, finish) + calculateOtherExpenses(begin, finish);
	}
}