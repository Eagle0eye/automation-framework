package mongo;


import DTO.Register;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegisterRepository {
    private static final Logger log = LoggerFactory.getLogger(RegisterRepository.class);
    private static final String COLLECTION = "auth-log";
    private static MongoCollection<Document> collection;

    public RegisterRepository(){
        MongoDatabase database = MongoDBClient.getDatabase();
        collection = database != null ? database.getCollection(COLLECTION) : null;
    }

    public void insertUser(Register user) {
        collection.insertOne(RegisterMapper.toDocument(user));
        log.info("Inserted user name: {} email: {}",user.getName(),user.getEmail());
    }

    public void deleteUser(String email) {
        Document filter = new Document("email", email);
        long deletedCount = collection.deleteOne(filter).getDeletedCount();

        if (deletedCount > 0) {
            log.info("Deleted user email: {}", email);
        } else {
            log.info("Not Found User with email {}", email);
        }
    }

    public void updateUserByEmail(String email, Register updatedUser) {
        Document filter = new Document("email", email);
        Document update = new Document("$set", RegisterMapper.toDocument(updatedUser));

        long modifiedCount = collection.updateOne(filter, update).getModifiedCount();

        if (modifiedCount > 0) {
            log.info("Updated user with email: {}", email);
        } else {
            log.warn("No user found with email: {} to update", email);
        }
    }



}
