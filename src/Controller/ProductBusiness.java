package Controller;

import java.util.ArrayList;

import Model.Product;
import Model.ProductGroup;
import Service.FileProcessor;

public class ProductBusiness {
	public static ArrayList<ProductGroup> list = FileProcessor.getProductListFromFile();
	
	public static String getNameById(int id) {
		for (ProductGroup p: list) {
			if (p.getProduct().getProductId() == id) {
				return p.getProduct().getName();
			}
		}
		return null;
	}
	
	public static void addProduct(Product p, int quantity) {
		list.add(new ProductGroup(p, quantity));
		FileProcessor.writeProductListIntoFile(list);
	}
	
	public static void deleteProduct(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProduct().getProductId() == id) {
				list.remove(i);
				break;
			}
		}
		FileProcessor.writeProductListIntoFile(list);
	}
	
	public static int updateQuantity(int id, int quantity) {
		for (ProductGroup p: list) {
			if (p.getProduct().getProductId() ==  id) {
				if (p.getQuantity() - quantity < 0) {
					return 0;
				}
				else {
					p.setQuantity(p.getQuantity() - quantity);
					FileProcessor.writeProductListIntoFile(list);
					return 1;
				}
			}
		}
		return -1;
	}
}
 