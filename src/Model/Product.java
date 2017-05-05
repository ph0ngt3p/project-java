package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Controller.ProductBusiness;

public abstract class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int productId;
	private String name;
	private String color;
	private int price;
	
	public static int getNextId() {
		int id = 1;
		ArrayList<ProductGroup> list = ProductBusiness.list;
		if (list.size() < 1)
			id = 1;
		else {
			id = list.get(list.size() - 1).getProduct().getProductId() + 1;
		}
		return id;
	}
	
	public static int getPriceById(int id) {
		ArrayList<ProductGroup> list = ProductBusiness.list;
		for (ProductGroup pg: list) {
			if (pg.getProduct().getProductId() == id) {
				return pg.getProduct().getPrice();
			}
		}
		return 0;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
