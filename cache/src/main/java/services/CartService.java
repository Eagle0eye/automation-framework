package services;

import models.ProductCache;
import models.SessionCache;
import repository.CacheRepository;

import java.time.Instant;
import java.util.Set;

public class CartService {
        public static void addToCart(ProductCache product) {
                SessionCache session = UserProfileService.getCurrentSession();
                session.getCartItems().add(product);
                updateSession(session);
        }

        public static void removeFromCart(String productName) {
                SessionCache session = UserProfileService.getCurrentSession();
                session.getCartItems().removeIf(p -> p.getProductName().equals(productName));
                updateSession(session);
        }

        public static Set<ProductCache> getCartItems() {
                return UserProfileService.getCurrentSession().getCartItems();
        }

        private static void updateSession(SessionCache session) {
                session.setLastUpdated(Instant.now());
                CacheRepository.saveSession(session);
        }
}
