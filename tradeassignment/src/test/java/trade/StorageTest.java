package trade;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import trade.domain.Trades;
import trade.exception.StorageException;
import trade.exception.TradeValidationException;
import trade.factory.StorageBuilder;
import trade.store.IStorage;
import trade.store.InMemoryStorage;
import trade.store.TradeStore;

public class StorageTest extends BaseTestCase {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testNullStorage() {
		IStorage storage = null;
		exception.expect(StorageException.class);
		new TradeStore(storage);
	}

	@Test
	public void testInMemoryStorage() {

		new TradeStore(storage);
	}

	@Test
	public void testDefaultStorage() {

		Assert.assertEquals(storage.getClass(), InMemoryStorage.class);
	}

	@Test
	public void testStorageNullTradeStore() {

		TradeStore tradeStore = new TradeStore(storage);
		exception.expect(TradeValidationException.class);
		exception.expectMessage("Trade type cannot be null");
		
		tradeStore.storeTrade(null);
	}

	@Test
	public void testStorageValidTradeStore() {

		TradeStore tradeStore = new TradeStore(storage);
		tradeStore.storeTrade(getTrade());
	}

	@Test
	public void testStorageGetTradeForNullObject() {

		exception.expect(TradeValidationException.class);
		exception.expectMessage("Trade cannot be null");
		Trades trade = null;

		storage.getTrade(trade);
		

	}

	@Test
	public void testStorageGetTradeForValidObject() {

		TradeStore tradeStore = new TradeStore(storage);
		Trades trade = getTrade();
		tradeStore.storeTrade(trade);
		Trades trade2 = storage.getTrade(trade);
		assertNotNull(trade2);
	}

	@Test
	public void testStorageGetTradeForNullTradeId() {

		exception.expect(TradeValidationException.class);
		exception.expectMessage("Trade cannot be null");
		Trades trade = getTrade();
		trade.setTradeId(null);

		assertNotNull(storage.getTrade(trade.getTradeId()));
	}

	@Test
	public void testStorageGetTradeStoreByTradeObject() {

		TradeStore tradeStore = new TradeStore(storage);
		Trades trade = getTrade();
		tradeStore.storeTrade(trade);
		assertNotNull(storage.getTrade(trade));

	}

	@Test
	public void testStorageGetTradeStoreByTradeId() {
		IStorage storage = StorageBuilder.getInstance().getStorage(0);
		TradeStore tradeStore = new TradeStore(storage);
		Trades trade = getTrade();
		tradeStore.storeTrade(trade);
		storage.getTrade(trade.getTradeId());

	}
}
