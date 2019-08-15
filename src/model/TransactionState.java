package model;

/**
 * the types of transactions allowed in the system
 * 
 * @author akshar
 *
 */
public enum TransactionState {
	
	DESCRIBE(0),
	PAY(1),
	PAY_SUCCESS(2),
	CANCEL(3),
	PROCESS(4);

	private int state;

	private TransactionState(int type) {
		this.state = type;
	}
	
	public int getState() {
		return state;
	}
}
