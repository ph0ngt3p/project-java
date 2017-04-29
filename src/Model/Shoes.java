package Model;

public class Shoes extends Product {
	
	private int size;

	public Shoes(String name, String color, int price, int size) {
		this.setProductId(getProductId());
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.size = size;
	}
	
	public Shoes(int productId, String name, String color, int price, int size) {
		this.setProductId(productId);
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String toString() {
		return new StringBuffer().append(this.getProductId()).append(" ").
				append(this.getName()).append(" ").
				append(this.getColor()).append(" ").
				append(this.getPrice()).append(" ").
				append(this.getSize()).toString();
	}
}
