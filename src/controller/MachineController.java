package controller;

import model.IngredientDetail;
import model.Product;

public class MachineController {

	InventoryManager icontroller;
	
	public MachineController(InventoryManager controller) {
		
		this.icontroller = controller;
	}
	
	public void process (Product product) {
		
		for (IngredientDetail d : product.ingredients) {
			icontroller.subtract(d.type, d.quantity);
		}
	}
}
