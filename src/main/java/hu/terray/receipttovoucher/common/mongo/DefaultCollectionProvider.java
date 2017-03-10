package hu.terray.receipttovoucher.common.mongo;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import java.math.BigDecimal;

import javax.inject.Singleton;

import org.mongojack.JacksonDBCollection;
import org.mongojack.internal.MongoJackModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.Inject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@Singleton
public class DefaultCollectionProvider{
    private final MongoClientProvider mongoClientProvider;
    private DB database;

    private static final String DB_NAME = "receipt-to-voucher";

    @Inject
    public DefaultCollectionProvider(final MongoClientProvider mongoClientProvider) {
        this.mongoClientProvider = mongoClientProvider;
    }

    public <T, K> JacksonDBCollection<T, K> getWrappedCollection(final String collectionName, final Class<T> type, final Class<K> keyType) {
        if (database == null) {
            init();
        }
        return getWrappedCollection(database.getCollection(collectionName), type, keyType);
    }

    private <T, K> JacksonDBCollection<T, K> getWrappedCollection(final DBCollection collection, final Class<T> type, final Class<K> keyType) {
        return JacksonDBCollection.wrap(collection, type, keyType, getJacksonMapper());
    }

    private ObjectMapper getJacksonMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        final JodaModule module = new JodaModule();
        module.addSerializer(BigDecimal.class, new ToStringSerializer());
        mapper.registerModule(module);
        mapper.setSerializationInclusion(NON_NULL);
        MongoJackModule.configure(mapper);
        return mapper;
    }

    // CHECKSTYLE:OFF
    @SuppressWarnings("deprecation")
    // CHECKSTYLE:ON
    private synchronized void init() {
        if (this.database == null) {
            final MongoClient mongoClient = mongoClientProvider.getMongoClient();
            this.database = mongoClient.getDB(DB_NAME);
        }
    }
}
