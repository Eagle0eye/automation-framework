package mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBClient {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "automation";
    private static final Logger log = LoggerFactory.getLogger(MongoDBClient.class);

    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase() {
        try {
            if (mongoClient == null) {
                mongoClient = MongoClients.create(CONNECTION_STRING);
                log.info("Connected to MongoDB at {}", CONNECTION_STRING);
            }
            return mongoClient.getDatabase(DATABASE_NAME);
        } catch (RuntimeException e) {
            log.error("Failed to connect to MongoDB: {}", e.getMessage());
            return null;
        }
    }


    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
