package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Controller.EmployeeBusiness;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int employeeId;
	private String name;
	private LocalDate dob;
	private String phone;
	private String username;
	private char[] password;
	private String accountType;
	
	public static int getNextId() {
		int id = 1;
		ArrayList<Employee> list = EmployeeBusiness.list;
		if (list.size() < 1)
			id = 1;
		else {
			id = list.get(list.size() - 1).getEmployeeId() + 1;
		}
		return id;
	}
	
//	public static Employee getEmployeeByUsername(String username) {
//		ArrayList<Employee> list = EmployeeBusiness.list;
//		for (Employee e: list) {
//			if (e.getUsername().equals(username))
//				return e;
//		}
//		return null;
//	}
	
	public Employee(String name, LocalDate dob, String phone, String username, char[] password, String type) {
		this.employeeId = getNextId();
		this.name = name;
		this.dob = dob;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.accountType = type;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
