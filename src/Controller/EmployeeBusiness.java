package Controller;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.Employee;
import Service.FileProcessor;

public class EmployeeBusiness {

	public static ArrayList<Employee> list = FileProcessor.getEmployeeListFromFile();
	
	private static boolean checkUsername(String username) {
		for (Employee e: list) {
			if (e.getUsername().equals(username)) {
				return false; 
			}
		}
		return true;
	}
	
	public static Employee getEmployeeById (int id) {
		for (Employee e: list) {
			if (e.getEmployeeId() == id) {
				return e;
			}
		}
		return null;
	}
	
	public static boolean addEmployee(String name, LocalDate dob, String phone, String username, char[] password, String type) {
		if (checkUsername(username)) {
			Employee e = new Employee(name, dob, phone, username, password, type);
			list.add(e);
			FileProcessor.writeEmployeeListIntoFile(list);
			return true;
		}
		else
			return false;
	}
	
	public static void updateEmployee(int id, LocalDate dob, String phone, char[] password, String type) {
		for (Employee e: list) {
			if (e.getEmployeeId() == id) {
				e.setDob(dob);
				e.setPhone(phone);
				e.setPassword(password);
				e.setAccountType(type);
				FileProcessor.writeEmployeeListIntoFile(list);
				break;
			}
		}
	}
	
	public static void deleteEmployee(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getEmployeeId() == id) {
				list.remove(i);
				break;
			}
		}
		FileProcessor.writeEmployeeListIntoFile(list);
	}
}
