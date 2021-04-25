package trade.factory;

import trade.store.IStorage;
import trade.store.InMemoryStorage;

/**
 * Builds the storage type based on the input, current implementation supports
 * in memory storage
 * 
 *
 */
public final class StorageBuilder {
	private IStorage storage;

	private static final StorageBuilder STORAGE_BUILDER = new StorageBuilder();

	/**
	 * private constructor, use getInstance method
	 */
	private StorageBuilder() {

	}

	public static final StorageBuilder getInstance() {
		synchronized (STORAGE_BUILDER) {
			return STORAGE_BUILDER;
		}
	}

	public IStorage getStorage(int storageType) {
		switch (storageType) {

		default:
			storage = new InMemoryStorage();
			break;
		}

		return storage;
	}
}
