package hu.terray.receipttovoucher.user.registration.dao;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import hu.terray.receipttovoucher.user.registration.dao.domain.User;
import hu.terray.receipttovoucher.user.registration.resource.domain.RegistrationRequest;

/**
 * Mongo db version of registration dao.
 */
public class MongoRegistrationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoRegistrationDao.class);

    public boolean register(RegistrationRequest registrationRequest){
        MongoClient client = new MongoClient("localhost",27017);
        DB db = client.getDB("receipt-to-voucher");
        DBCollection collection = db.getCollection("user");

        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(collection, User.class, String.class);
        ObjectMapper om = new ObjectMapper();
        User userToInsert = new User();
        userToInsert.setEmailAddress(registrationRequest.getEmailAddress());
        userToInsert.setFirstName(registrationRequest.getFirstName());
        userToInsert.setLastName(registrationRequest.getLastName());
        userToInsert.setPassword(registrationRequest.getPassword());

        WriteResult<User, String> result = coll.insert(userToInsert);

        client.close();

        return true;
    }
}
