package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

	public String name;
	public List<IngredientDetail> ingredients = new ArrayList<>();
	
	public void describe () {
		
		System.out.println("Product name: "+name);
	}
}
