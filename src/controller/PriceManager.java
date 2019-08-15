package controller;

import java.util.HashMap;
import java.util.Map;

import model.ProductSize;

/**
 * manages the price per unit of the product
 * 
 * @author akshar
 *
 */
public class PriceManager {

	private Map<Integer, Map<ProductSize, Integer>> prices = new HashMap<>();
	
	/**
	 * set new unit price for the product
	 * @param product
	 * @param price
	 */
	public void set (int productId, ProductSize size, int price) {
		
		Map<ProductSize, Integer> sprices;
		
		if (prices.containsKey(productId))
			sprices = prices.get(productId);
		else
		{
			sprices = new HashMap<>();
			prices.put(productId, sprices);
		}
		
		sprices.put(size, price);
	}
	
	/**
	 * retrieve the price for the product
	 * 
	 * @param product
	 * @return
	 */
	public int get (int productId, ProductSize size) {
		
		return prices.get(productId).get(size);
	}
}
