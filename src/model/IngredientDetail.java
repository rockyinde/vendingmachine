package model;

public class IngredientDetail {

	public int quantity;
	public IngredientType type;
	
	public IngredientDetail(IngredientType type, int qty) {
		this.quantity = qty;
		this.type = type;
	}
}
