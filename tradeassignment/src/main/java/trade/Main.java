package trade;

import trade.factory.StorageBuilder;
import trade.store.IStorage;
import trade.validator.IValidator;
import trade.validator.TradeValidator;

/**
 * Main class for run validator
 * 
 *
 */
public class Main {
	private IStorage storage = StorageBuilder.getInstance().getStorage(0);
	private IValidator validator = TradeValidator.getInstance();

	public Main() {
		setup();
	}

	/**
	 * Main class to run
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

	private void setup() {
		this.validator.setStorage(getStorage());
	}

	/**
	 * Get the validator created
	 * 
	 * @return IValidator 
	 */
	public IValidator getValidator() {
		return validator;
	}

	/**
	 * Gets the Storage system (Inmemory)
	 * @return
	 */
	public IStorage getStorage() {
		return storage;
	}

}
