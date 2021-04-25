package trade;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import trade.domain.Trades;
import trade.exception.TradeNotFoundException;
import trade.exception.TradeValidationException;

public class TradeValidatorTest extends BaseTestCase {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void beforeTest() {

		validator.setStorage(storage);
	}

	@Test
	public void testNullTradeForValidator() {
		Trades trade = null;
		exception.expect(TradeValidationException.class);
		validator.validate(trade);
	}

	@Test
	public void testTradeForValidator() {

		Trades trade = getTrade();
		storage.store(trade);
		validator.validate(trade);
	}

	@Test
	public void testTradeNotFoundForValidator() {
		Trades trade = getTrade();
		trade.setTradeId(UUID.randomUUID().toString());

		exception.expect(TradeNotFoundException.class);
		validator.validate(trade);
	}

	@Test
	public void testVersionTradeForValidator() {

		Trades tradeToStore = getTrade();
		tradeToStore.setVersion(3);
		storage.store(tradeToStore);
		Trades trade = getTrade();
		trade.setVersion(1);

		exception.expect(TradeValidationException.class);
		validator.validate(trade);
	}

	@Test
	public void testNullMaturityDateTradeForValidator() {

		Trades tradeToStore = getTrade();
		storage.store(tradeToStore);
		Trades trade = getTrade();
		trade.setMaturityDate(null);

		exception.expect(TradeValidationException.class);
		validator.validate(trade);
	}

	@Test
	public void testLessMaturityDateTradeForValidator() {

		Trades tradeToStore = getTrade();
		storage.store(tradeToStore);
		Trades trade = getTrade();
		Date lessMaturityDate = getLessThenTodaysDate();
		trade.setMaturityDate(lessMaturityDate);

		exception.expect(TradeValidationException.class);
		validator.validate(trade);
	}

	

	@Test
	public void testExpireTradeForValidator() {

		Trades tradeToStore = getTrade();
		Date lessThenTodaysDate = getLessThenTodaysDate();
		tradeToStore.setCreatedDate(lessThenTodaysDate);
		storage.store(tradeToStore);
		validator.expire();
		 storage.getTrade(tradeToStore);
	
	}

	@Test
	public void testExpireStopScheduleForValidator() {
		Trades tradeToStore = getTrade();
		storage.store(tradeToStore);
		validator.stopExpireSchedule();
	}

}
