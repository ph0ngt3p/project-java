package Controller;

import java.util.ArrayList;

import Model.Bag;
import Model.Clothes;
import Model.Jewellery;
import Model.Product;
import Model.ProductGroup;
import Model.Shoes;
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
	
	public static ProductGroup getGroupById(int id) {
		for (ProductGroup pg: list) {
			if (pg.getProduct().getProductId() == id) {
				return pg;
			}
		}
		return null;
	}
	
	public static void addProduct(Product p, int quantity) {
		list.add(new ProductGroup(p, quantity));
		FileProcessor.writeProductListIntoFile(list);
	}
	
	public static void updateProduct(int id, String type, String name, String color, int price, String size, String material, int quantity) {
		for (ProductGroup e: list) {
			if (e.getProduct().getProductId() == id) {
				System.out.println(type);
				if (type.equals("Clothes")) {
					e.setProduct(new Clothes(id, name, color, price, size));
					e.setQuantity(quantity);
					System.out.println(e.toString());
				}
				else if (type.equals("Shoes")) {
					e.setProduct(new Shoes(id, name, color, price, Integer.parseInt(size)));
					e.setQuantity(quantity);
				}
				else if (type.equals("Bag")) {
					e.setProduct(new Bag(id, name, color, price, Bag.getArraySize(size)));
					e.setQuantity(quantity);
				}
				else if (type.equals("Jewellery")) {
					e.setProduct(new Jewellery(id, name, color, price, material));
					e.setQuantity(quantity);
				}
				FileProcessor.writeProductListIntoFile(list);
				break;
			}
		}
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
 