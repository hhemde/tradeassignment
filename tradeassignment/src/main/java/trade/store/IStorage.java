package trade.store;

import java.util.Collection;

import trade.domain.Trades;

/**
 * 
 * Storage interface for implementing different storage to store the trade or
 * get the trades
 *
 */
public interface IStorage {

	public void store(Trades trade);

	public Trades getTrade(Trades trade);

	public Trades getTrade(String tradeId);

	public Collection<Trades> getAllTrades();
}
