package controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import model.TransactionState;

/**
 * wrapper class to maintain and manage txn state
 * we could simply change the implementation here to save the state to a database in the future
 * 
 * @author akshar
 *
 */
public class TransactionManager {

	/**
	 * concurrent hashmap will suffice for our needs because:
	 * any client interacting with our system will modify the hash map via a single thread. In other words, there can't be two threads per client.
	 */
	Map<Integer, TransactionState> txns = new ConcurrentHashMap<>();
	
	/**
	 * using synchronized 
	 * 
	 * @param clientId
	 * @param state
	 */
	public void update (int clientId, TransactionState state) {
		
		txns.put(clientId, state);
	}
	
	public boolean isValid (int clientId, TransactionState state) {
		
		if (state == TransactionState.DESCRIBE)
		{
			txns.put(clientId, TransactionState.DESCRIBE);
			return true;
		}
		
		return (txns.get(clientId).getState() <= state.getState());
	}
	
	public void cleanUp (int clientId) {
		
		txns.remove(clientId);
	}
}
