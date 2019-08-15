package model;

public class Tea extends Product {

	public Tea () {
		
		this.name = "Tea";
		ingredients.add(new IngredientDetail(IngredientType.WATER, 10));
		ingredients.add(new IngredientDetail(IngredientType.MILK, 10));
	}
}
