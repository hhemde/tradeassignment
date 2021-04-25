package trade.store;

import trade.domain.Trades;
import trade.exception.StorageException;
import trade.exception.TradeValidationException;
import trade.utils.TradeConstants;

/**
 * 
 * Trade store to store the trades
 *
 */
public class TradeStore {

	private IStorage storage;

	private TradeStore() {

	}

	public TradeStore(IStorage storage) {
		if (storage == null) {
			throw new StorageException(TradeConstants.STORAGE_TYPE_NULL_MSG);
		}
		this.storage = storage;
	}

	public void storeTrade(Trades trade) {
		if (trade == null) {
			throw new TradeValidationException(TradeConstants.TRADE_TYPE_NULL_MSG);
		}
		storage.store(trade);
	}

}
