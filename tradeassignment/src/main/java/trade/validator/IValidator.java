package trade.validator;

import trade.domain.Trades;
import trade.store.IStorage;

public interface IValidator {

	public void validate(Trades trade);

	public void setStorage(IStorage storage);

	public void expire();

	public void stopExpireSchedule();
}
