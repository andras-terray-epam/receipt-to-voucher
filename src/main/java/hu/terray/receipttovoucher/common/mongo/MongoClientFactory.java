package hu.terray.receipttovoucher.common.mongo;

import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

/**
 * Mongo client factory.
 */
@Singleton
public class MongoClientFactory {
    private static final String MONGO_SERVER_ADDRESS = "localhost:27017";

    private MongoClient mongoClient;

    /**
     * Create mongo client if does not exist.
     *
     * @return mongo db client.
     */
    public MongoClient createMongoClient() {
        if (mongoClient == null) {
            MongoClientOptions clientOptions = MongoClientOptions.builder().build();
            mongoClient = new MongoClient(MONGO_SERVER_ADDRESS, clientOptions);
        }
        return mongoClient;
    }
}
