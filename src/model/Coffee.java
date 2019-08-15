package model;

public class Coffee extends Product {

	public Coffee () {
		
		this.name = "Coffee";
		ingredients.add(new IngredientDetail(IngredientType.COFFEE, 10));
		ingredients.add(new IngredientDetail(IngredientType.WATER, 10));
		ingredients.add(new IngredientDetail(IngredientType.MILK, 10));
	}
}
