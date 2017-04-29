package Model;

public class Bag extends Product {
	
	private int height;
	private int width;
	private int depth;
	
	public int[] getArraySize(String size) {
		int[] arr = new int[3];
		String[] tokens = size.split("x");
		arr[0] = Integer.parseInt(tokens[0].trim());
		arr[1] = Integer.parseInt(tokens[1].trim());
		arr[2] = Integer.parseInt(tokens[2].trim());
		return arr;
	}
	
	public String getStringSize(int[] size) {
		StringBuffer sb = new StringBuffer().append(size[0]).append(" ").append(size[1]).append(" ").append(size[2]);
		return sb.toString();
	}
	
	public String getStringSize(int height, int width, int depth) {
		StringBuffer sb = new StringBuffer().append(height).append("x").append(width).append("x").append(depth);
		return sb.toString();
	}
	
	public Bag(String name, String color, int price, int[] size) {
		this.setProductId(getProductId());
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.height = size[0];
		this.width = size[1];
		this.depth = size[2];
	}
	
	public Bag(int productId, String name, String color, int price, int[] size) {
		this.setProductId(productId);
		this.setName(name);
		this.setColor(color);
		this.setPrice(price);
		this.height = size[0];
		this.width = size[1];
		this.depth = size[2];
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String toString() {
		return new StringBuffer().append(this.getProductId()).append(" ").
				append(this.getName()).append(" ").
				append(this.getColor()).append(" ").
				append(this.getPrice()).append(" ").
				append(this.getStringSize(this.height, this.width, this.depth)).toString();
	}
}
