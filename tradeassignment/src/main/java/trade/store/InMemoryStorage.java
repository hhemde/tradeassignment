package trade.store;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import trade.domain.Trades;
import trade.exception.TradeValidationException;
import trade.utils.TradeConstants;

/**
 * InMemory storage class for storing trade object, this should be used in
 * production, please implement own storage mechanism
 * 
 * 
 *
 */
public class InMemoryStorage implements IStorage {
	private ConcurrentMap<String, Trades> storage = new ConcurrentHashMap<String, Trades>();

	public void store(Trades trade) {
		storage.put(trade.getTradeId(), trade);
	}

	public Trades getTrade(Trades trade) {
		if (trade == null) {
			throw new TradeValidationException(TradeConstants.TRADE_NULL_MSG);
		}
		return storage.get(trade.getTradeId());
	}

	public Trades getTrade(String tradeId) {
		if (tradeId == null) {
			throw new TradeValidationException(TradeConstants.TRADE_NULL_MSG);
		}
		return storage.get(tradeId);
	}

	public Collection<Trades> getAllTrades() {

		return (Collection<Trades>) storage.values();
	}

}
