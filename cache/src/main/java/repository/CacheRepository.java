package repository;

import models.ProductCache;
import models.SessionCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CacheRepository {
    private static final Map<String, Object> dataStore = new HashMap<>();
    private static final String SESSION_PREFIX = "session-";

    public static void saveSession(SessionCache session) {
        dataStore.put(SESSION_PREFIX + session.getEmail(), session);
    }

    public static Optional<SessionCache> getSession(String email) {
        Object session = dataStore.get(SESSION_PREFIX + email);
        return session instanceof SessionCache
                ? Optional.of((SessionCache) session)
                : Optional.empty();
    }

    public static void saveProduct(ProductCache product) {
        dataStore.put("product-" + product.getProductName(), product);
    }

    public static Optional<ProductCache> getProduct(String productName) {
        Object product = dataStore.get("product-" + productName);
        return product instanceof ProductCache
                ? Optional.of((ProductCache) product)
                : Optional.empty();
    }
}