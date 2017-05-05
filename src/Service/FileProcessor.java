package Service;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Employee;
import Model.Expense;
import Model.ProductGroup;
import Model.Transaction;

public class FileProcessor {
	private static final String productFile = "productFile";
	private static final String employeeFile = "employeeFile";
	private static final String transactionFile = "transactionFile";
	private static final String expenseFile = "expenseFile";
	
	public static ArrayList<Employee> getEmployeeListFromFile() {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		try {
			FileInputStream fileIn = new FileInputStream(employeeFile);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			boolean eof = false;
			Object obj = null;
			while (!eof) {
				try {
					obj = objectIn.readObject();
					if (obj instanceof Employee) {
						Employee e = (Employee) obj;
						employeeList.add(e);
					}
				} catch (EOFException e) {
					eof = true;
				}
			}
			objectIn.close();

		} catch (FileNotFoundException e) {
			System.err.println("Create new employee file!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}
	
	public static void writeEmployeeListIntoFile(ArrayList<Employee> list) {
		try {
			FileOutputStream fileOut = new FileOutputStream(employeeFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			for (Employee e : list) {
				objectOut.writeObject(e);
				objectOut.reset();
			}
			objectOut.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static ArrayList<ProductGroup> getProductListFromFile() {
		ArrayList<ProductGroup> productList = new ArrayList<ProductGroup>();
		try {
			FileInputStream fileIn = new FileInputStream(productFile);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			boolean eof = false;
			Object obj = null;
			while (!eof) {
				try {
					obj = objectIn.readObject();
					if (obj instanceof ProductGroup) {
						ProductGroup e = (ProductGroup) obj;
						productList.add(e);
					}
				} catch (EOFException e) {
					eof = true;
				}
			}
			objectIn.close();

		} catch (FileNotFoundException e) {
			System.err.println("Create new product file!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	public static void writeProductListIntoFile(ArrayList<ProductGroup> list) {
		try {
			FileOutputStream fileOut = new FileOutputStream(productFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			for (ProductGroup e : list) {
				objectOut.writeObject(e);
				objectOut.reset();
			}
			objectOut.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static ArrayList<Transaction> getTransactionListFromFile() {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			FileInputStream fileIn = new FileInputStream(transactionFile);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			boolean eof = false;
			Object obj = null;
			while (!eof) {
				try {
					obj = objectIn.readObject();
					if (obj instanceof Transaction) {
						Transaction e = (Transaction) obj;
						transactionList.add(e);
					}
				} catch (EOFException e) {
					eof = true;
				}
			}
			objectIn.close();

		} catch (FileNotFoundException e) {
			System.err.println("Create new transaction file!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionList;
	}
	
	public static void writeTransactionListIntoFile(ArrayList<Transaction> list) {
		try {
			FileOutputStream fileOut = new FileOutputStream(transactionFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			for (Transaction e : list) {
				objectOut.writeObject(e);
				objectOut.reset();
			}
			objectOut.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static ArrayList<Expense> getExpenseListFromFile() {
		ArrayList<Expense> expenseList = new ArrayList<Expense>();
		try {
			FileInputStream fileIn = new FileInputStream(expenseFile);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			boolean eof = false;
			Object obj = null;
			while (!eof) {
				try {
					obj = objectIn.readObject();
					if (obj instanceof Expense) {
						Expense e = (Expense) obj;
						expenseList.add(e);
					}
				} catch (EOFException e) {
					eof = true;
				}
			}
			objectIn.close();

		} catch (FileNotFoundException e) {
			System.err.println("Create new expense file!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return expenseList;
	}
	
	public static void writeExpenseListIntoFile(ArrayList<Expense> list) {
		try {
			FileOutputStream fileOut = new FileOutputStream(expenseFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

			for (Expense e : list) {
				objectOut.writeObject(e);
				objectOut.reset();
			}
			objectOut.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
