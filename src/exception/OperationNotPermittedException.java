package exception;

public class OperationNotPermittedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public OperationNotPermittedException(String msg) {
		this.message = msg;
	}
	
	public String getMessage () {
		return message;
	}
}
