package Model;

import java.io.Serializable;

public class ProductGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Product product;
	private int quantity;
	
	public ProductGroup(Product p, int quantity) {
		this.product = p;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
