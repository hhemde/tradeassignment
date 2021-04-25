package trade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import trade.domain.Trades;
import trade.factory.StorageBuilder;
import trade.store.IStorage;
import trade.validator.IValidator;
import trade.validator.TradeValidator;

public class BaseTestCase {
	private SimpleDateFormat sdfo = new SimpleDateFormat("dd/MM/yyyy");

	protected IValidator validator = TradeValidator.getInstance();
	protected IStorage storage = StorageBuilder.getInstance().getStorage(0);
	
	protected Trades getTrade() {
		Trades trades = new Trades();
		trades.setBookId("B1");
		trades.setCountryPartyId("CP-1");
		// DD/MM/YYYY

		Date currentDate = new Date();
		String format = sdfo.format(currentDate);

		try {
			trades.setCreatedDate(sdfo.parse(format));
		} catch (ParseException e) {
			e.printStackTrace();
			trades.setCreatedDate(currentDate);
		}
		trades.setExpired(Boolean.FALSE);
		trades.setMaturityDate(currentDate);
		trades.setTradeId("T1");
		trades.setVersion(0);
		return trades;
	}

	protected void loadTrades() {
		int len = 10;
		int nub = 0;
		for (int i = 0; i < len; i++) {
			Trades trades = new Trades();
			trades.setBookId("B" + i);
			trades.setCountryPartyId("CP-" + i);
			// DD/MM/YYYY
			Date currentDate = new Date();

			if (i % 2 == 0) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				cal.add(Calendar.DAY_OF_MONTH, nub--);
				String format = sdfo.format(cal.getTime());
				try {
					currentDate = sdfo.parse(format);
				} catch (ParseException e) {
					trades.setCreatedDate(currentDate);
				}
				trades.setCreatedDate(currentDate);
			} else {
				String format = sdfo.format(currentDate);

				try {
					currentDate = sdfo.parse(format);
				} catch (ParseException e) {
					trades.setCreatedDate(currentDate);
				}
				trades.setCreatedDate(currentDate);
			}

			trades.setExpired(Boolean.FALSE);
			trades.setMaturityDate(currentDate);
			trades.setTradeId("T" + i);
			trades.setVersion(0);
			storage.store(trades);
		}

	}

	protected Date getLessThenTodaysDate() {
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.DAY_OF_MONTH, -2);
		String format = sdfo.format(cal.getTime());
		try {
			return currentDate = sdfo.parse(format);
		} catch (ParseException e) {
			return currentDate;
		}
	}

	protected Date getUnFormattedDate() {
		return new Date();

	}

	protected void printTrades() {
		System.out.println("==========================================");
		Collection<Trades> allTrades = storage.getAllTrades();
		for (Trades trades : allTrades) {
			System.out.println(trades.getBookId() + " " + trades.getCreatedDate());
		}
		System.out.println("==========================================");
	}
}
