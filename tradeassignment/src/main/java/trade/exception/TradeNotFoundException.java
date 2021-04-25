package trade.exception;

/**
 * 
 * Trade object exception, thrown in case of problems with trade object
 * e.g. Trade cannot be null
 *
 */
public class TradeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public TradeNotFoundException() {

	}

	public TradeNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
