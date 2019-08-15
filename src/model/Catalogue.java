package model;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {

	List<Product> catalogue = new ArrayList<>();
	
	public void initalizeDefaultProducts () {
		
		catalogue.add(new Coffee());
		catalogue.add(new Tea());
	}
	
	public void describe() {
		
		int i = 0;
		for (Product p : catalogue) {
			System.out.println("Item "+ i++);
			p.describe();
		}
	}
}
