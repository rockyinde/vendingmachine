package controller;

import model.IngredientType;
import model.ProductSize;

public class AdminController {

	InventoryManager imanager;
	PriceManager pmanager;
	
	public AdminController(InventoryManager icontroller, PriceManager pmanager) {
		this.imanager = icontroller;
		this.pmanager = pmanager;
		
	}
	
	public void initializeInventory() {
		
		imanager.add(IngredientType.COFFEE, 1000);
		imanager.add(IngredientType.WATER, 1000);
		imanager.add(IngredientType.MILK, 1000);
	}
	
	public void setDefaultProductPrices() {
		
		pmanager.set(0, ProductSize.SMALL, 100);		// coffee
		pmanager.set(0, ProductSize.MEDIUM, 150);		// coffee
		pmanager.set(0, ProductSize.LARGE, 200);		// coffee

		pmanager.set(1, ProductSize.SMALL, 200);		// tea
		pmanager.set(1, ProductSize.MEDIUM, 250);		// tea
		pmanager.set(1, ProductSize.LARGE, 300);		// tea
	}
	
	public void initialize() {
		
		initializeInventory();
		setDefaultProductPrices();
	}
}
