package Model;

public class Jewellery extends Product {
	
	private String material;

	public Jewellery (String name, String color, int price, String material) {
		this.setProductId(getProductId());
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.material = material;
	}
	
	public Jewellery (int productId, String name, String color, int price, String material) {
		this.setProductId(productId);
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.material = material;
	}
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String toString() {
		return new StringBuffer().append(this.getProductId()).append(" ").
				append(this.getName()).append(" ").
				append(this.getColor()).append(" ").
				append(this.getPrice()).append(" ").
				append(this.getMaterial()).toString();
	}
}
