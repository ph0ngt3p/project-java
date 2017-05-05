package Controller;

import java.util.ArrayList;

import Model.Transaction;
import Service.FileProcessor;

public class TransactionBusiness {
	public static ArrayList<Transaction> list = FileProcessor.getTransactionListFromFile();
	
	public static int addTransaction(Transaction trans) {
		int check = ProductBusiness.updateQuantity(trans.getProductId(), trans.getQuantity());
		if (check == 0) {
			return 0;
		}
		else if (check == -1) {
			return -1;
		}
		else {
			list.add(trans);
			FileProcessor.writeTransactionListIntoFile(list);
			return 1;
		}
	}
	
	public static void deleteTransaction(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTransactionId() == id) {
				list.remove(i);
				ProductBusiness.updateQuantity(list.get(i).getProductId(), list.get(i).getQuantity() * (-1));
			}
		}
		FileProcessor.writeTransactionListIntoFile(list);
	}
}
