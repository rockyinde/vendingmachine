package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IngredientDetail;
import model.IngredientType;

public class InventoryManager {
	
	Map<IngredientType, Integer> inventory = new HashMap<>();
	
	public void add (IngredientType type, int qty) {
		
		inventory.put(type, qty);
	}

	public void subtract (IngredientType type, int qty) {
		
		inventory.put(type, inventory.get(type) - qty);
	}
	
	public boolean isAvailable (List<IngredientDetail> ingredients) {
		
		for (IngredientDetail i : ingredients) {
			
			if (!(inventory.get(i.type) > i.quantity))
				return false;
		}
		return true;
	}
}
