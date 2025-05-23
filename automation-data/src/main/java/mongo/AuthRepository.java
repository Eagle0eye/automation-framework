package mongo;

import DTO.Login;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;





public class AuthRepository {
    private static final Logger log = LoggerFactory.getLogger(AuthRepository.class);
    private static final String COLLECTION = "auth-log";
    private static MongoCollection<Document> collection = MongoDBClient.getDatabase().getCollection(COLLECTION);

    public List<Login> getAllUsers() {

        List<Login> users = new ArrayList<>();

        for (Document doc : collection.find()) {
            users.add(Login.builder()
                            .email(doc.getString("email"))
                            .password(doc.getString("password"))
                    .build());
        }

        log.info("Fetched {} users from database", users.size());
        return users;
    }

    public boolean userExists(String email) {
        Document filter = new Document("email", email);
        return collection.find(filter).first() != null;
    }
}
