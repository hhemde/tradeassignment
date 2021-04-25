package trade.validator;

import java.util.Collection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import trade.domain.Trades;
import trade.exception.TradeNotFoundException;
import trade.exception.TradeValidationException;
import trade.store.IStorage;
import trade.utils.TradeConstants;

/**
 * 
 * Validator implementation for trade object to check version maturity date and
 * then store it.
 * It starts a schedule task for every one second to expire the trades which are less than todays date from the store
 *
 */
public final class TradeValidator implements IValidator {
	private IStorage storage;
	private static final TradeValidator TRADE_VALIDATOR = new TradeValidator();
	private TimerTask task;
	private Timer timer;

	public void validate(Trades trade) {
		if (trade == null) {
			throw new TradeValidationException(TradeConstants.TRADE_NULL_MSG);
		}
		lowerVersion(trade);
		lessMaturityDate(trade);
		storage.store(trade);
	}

	private void lowerVersion(Trades trade) {
		Trades foundTrade = validateAndReturnTradeObject(trade);
		if (trade.getVersion() < foundTrade.getVersion()) {
			throw new TradeValidationException(TradeConstants.TRADE_VERSION_LOWER_MSG);
		}

	}

	private void lessMaturityDate(Trades trade) {
		Trades foundTrade = validateAndReturnTradeObject(trade);

		Date inComingmaturityDate = trade.getMaturityDate();
		if (inComingmaturityDate == null) {
			throw new TradeValidationException(TradeConstants.TRADE_NOT_FOUND_MSG);
		}

		Date foundMaturityDate = foundTrade.getMaturityDate();
		if (inComingmaturityDate.before(foundMaturityDate)) {
			throw new TradeValidationException(TradeConstants.TRADE_MATURITY_DATE_MISS_MATCH_MSG);
		}

	}

	public void expire() {
		if (storage != null) {
			Date today = new Date();
			Collection<Trades> allTrades = storage.getAllTrades();
			for (Trades trades : allTrades) {
				if (trades.getMaturityDate().before(today)) {
					trades.setExpired(Boolean.TRUE);
				}
			}
		}

	}

	private Trades validateAndReturnTradeObject(Trades trade) {

		Trades foundTrade = storage.getTrade(trade);
		if (foundTrade == null) {
			throw new TradeNotFoundException(TradeConstants.TRADE_NOT_FOUND_MSG);
		}
		return foundTrade;

	}

	private void scheduleTask() {
		System.out.println("Schedule task to update exipry");
		this.task = new TimerTask() {
			public void run() {
				System.out.println("Trade update for expiry performed on: " + new Date());
				TradeValidator.getInstance().expire();
			}
		};
		this.timer = new Timer("Timer");

		long delay = 1000L;
		timer.schedule(task, delay);
	}

	public void stopExpireSchedule() {
		this.task.cancel();
		timer.cancel();
		timer.purge();

	}

	public void setStorage(IStorage storage) {
		this.storage = storage;
	}

	private TradeValidator() {
		scheduleTask();
	}

	public static final TradeValidator getInstance() {
		synchronized (TRADE_VALIDATOR) {
			return TRADE_VALIDATOR;
		}
	}
}
