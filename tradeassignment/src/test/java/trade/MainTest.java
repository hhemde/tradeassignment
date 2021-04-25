package trade;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import trade.Main;
import trade.domain.Trades;

public class MainTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testTradeForValidator() {
		Main main = new Main();
		Trades trade = new Trades();
		trade.setBookId("B1");
		trade.setCountryPartyId("CP-1");
		
		Date currentDate = new Date();
		trade.setCreatedDate(currentDate);
		
		trade.setExpired(Boolean.FALSE);
		trade.setMaturityDate(currentDate);
		trade.setTradeId("T1");
		trade.setVersion(0);
		main.getStorage().store(trade);
		main.getValidator().validate(trade);

	}
}
