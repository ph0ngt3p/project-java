package Controller;

import java.util.ArrayList;

import Model.Expense;
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
}
