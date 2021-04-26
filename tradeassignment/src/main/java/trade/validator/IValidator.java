package trade.validator;

import trade.domain.Trades;
import trade.store.IStorage;

public interface IValidator {

	/**
	 * Validates the trade data for lower version will be rejected and maturity date
	 * is less then today
	 * 
	 * @param trade object with required data
	 */
	public void validate(Trades trade);

	/**
	 * Set the storage type, current implementation supports InMemoryStorage
	 * 
	 * @param storage
	 */
	public void setStorage(IStorage storage);

	/**
	 * automatically update the expire flag if in a store the trade crosses the
	 * maturity date
	 */
	public void expire();

	/**
	 * Stops the schedule task for expire
	 */
	public void stopExpireSchedule();
}
