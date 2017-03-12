package hu.terray.receipttovoucher.common.mongo;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;

@Singleton
public class MongoClientProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoClientProvider.class);
    private static final String RETRY_INITIALISE_MONGO_MESSAGE = "Failed to initialise Mongo, I'll try again in 5 seconds";

    private static final int FIVE_SECONDS = 5000;

    private final MongoClientFactory factory;

    private MongoClient mongoClient;

    private final Object mcLock = new Object();

    @Inject
    public MongoClientProvider(final MongoClientFactory factory) {
        this.factory = factory;
    }

    public MongoClient getMongoClient() {
        if (mongoClient == null) {
            synchronized (mcLock) {
                start();
            }
        }
        return mongoClient;
    }

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

    public void stop() {
        mongoClient.close();
    }
}
