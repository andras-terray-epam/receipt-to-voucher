package hu.terray.receipttovoucher.common.mongo;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mongo db client provider.
 */
@Singleton
public class MongoClientProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoClientProvider.class);
    private static final String RETRY_INITIALISE_MONGO_MESSAGE = "Failed to initialise Mongo, I'll try again in 5 seconds";

    private static final int FIVE_SECONDS = 5000;

    private final MongoClientFactory factory;

    private MongoClient mongoClient;

    private final Object mcLock = new Object();

    /**
     * Constructor with necessary dependencies.
     *
     * @param factory dependency.
     */
    @Inject
    public MongoClientProvider(final MongoClientFactory factory) {
        this.factory = factory;
    }

    /**
     * Creating mongo client if does not exist already.
     *
     * @return mongo client.
     */
    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            synchronized (mcLock) {
                start();
            }
        }
        return mongoClient;
    }

    /**
     * Initiating mongo client at application start.
     */
    public void start() {
        if (mongoClient == null) {
            mongoClient = initMongo();
        }
    }

    private MongoClient initMongo() {
        boolean mongoIsGood = false;
        MongoClient client = null;
        while (!mongoIsGood) {
            try {
                client = factory.createMongoClient();
                mongoIsGood = true;
            } catch (final Exception ex) {
                LOGGER.error(RETRY_INITIALISE_MONGO_MESSAGE, ex);
                try {
                    Thread.sleep(FIVE_SECONDS);
                } catch (final Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return client;
    }

    /**
     * Closing client at application closing.
     */
    public void stop() {
        mongoClient.close();
    }
}
