package Model;

public class Clothes extends Product {
	
	private String size;
	
	public Clothes(String name, String color, int price, String size) {
		this.setProductId(getProductId());
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.size = size;
	}
	
	public Clothes(int productId, String name, String color, int price, String size) {
		this.setProductId(productId);
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.size = size;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
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
