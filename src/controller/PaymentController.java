package controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import model.TransactionState;

public class PaymentController {

	int amount;
	
	// maintain a map of client ID --> cash tendered so far
	// concurrent hash map --> locking at bucket level , i.e., at the client level
	Map<Integer, Integer> txns = new ConcurrentHashMap<>();
	
	public PaymentController (int total) {
		this.amount = total;
	}
	
	/**
	 * is the amount entered by the user at the client sufficient for the product cost so far?
	 * @param client
	 * @param cost
	 * @param tender
	 * @return
	 */
	private boolean isTenderSufficient (int client, int cost, int tender) {
		
		// check if a txn already exists
		if (txns.containsKey(client))
			tender += txns.get(client);
		
		txns.put(client, tender);
		return (tender >= cost);
	}
	
	/**
	 * remove the client from our txns cache, as the client has provided with sufficient change
	 * 
	 * @param client
	 */
	private void clearTransaction (int client) {
		
		txns.remove(client);
	}
	
	/**
	 * @param cost the cost of the product
	 * @param tender the amount that has been fed by the user
	 * @return response semantics:
	 * 	-1 : for representing the amount is insufficient and product cannot be vended any further
	 * 	non-negative integer: tendered sufficient amount, we need to return this change and proceed with vending
	 */
	public int process (int client, int cost, int tender) {
		
		if(!isTenderSufficient(client, cost, tender))
			return -1;
		else
			clearTransaction(client);
		
		tender = txns.get(client);
		int change = tender - cost;
		amount += cost;
		
		return change;
	}
	
	/**
	 * refund the amount that has been tendered by this client
	 * 
	 * @param client
	 * @return
	 * 	amount to refund
	 * 	0: if no txn exist
	 */
	public int cancelTransaction (int client) {
		
		if (txns.containsKey(client)) {
			
			int tender = txns.get(client);
			txns.remove(client);
			
			return tender;
		} else
			return 0;
	}
}
