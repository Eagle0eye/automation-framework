package mongo;

import DTO.Register;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
public class MongoDBRepository {
    private static final String COLLECTION = "auth-log";
    private static MongoCollection<Document> collection;
    public MongoDBRepository(){
        MongoDatabase database = MongoDBClient.getDatabase();
        collection = database.getCollection(COLLECTION);

    }

    public void insertUser(Register user) {
        Document doc = new Document("responseCode", 200)
                .append("user", MongoDBMapper.toDocument(user));
        collection.insertOne(doc);
        log.info("Inserted user name: {} email: {}",user.getName(),user.getEmail());
    }
    public void deleteUser(String email) {
        Do

    }
}
