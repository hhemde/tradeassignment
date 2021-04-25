package trade.exception;

/**
 * 
 * Trade validation exception in case on version check, maturity date check
 *
 */
public class TradeValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public TradeValidationException() {

	}

	public TradeValidationException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
