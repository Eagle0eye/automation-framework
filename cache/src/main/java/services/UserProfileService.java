package services;

import models.SessionCache;
import models.UserInfo;
import repository.CacheRepository;

import java.time.Instant;
import java.util.HashSet;

public class UserProfileService {
    private static String currentUser;

    public static void login(String email, UserInfo userInfo) {
        SessionCache session = SessionCache.builder()
                .email(email)
                .accountInfo(userInfo)
                .cartItems(new HashSet<>())
                .createdAt(Instant.now())
                .lastUpdated(Instant.now())
                .build();

        CacheRepository.saveSession(session);
        currentUser = email;
    }

    public static void logout() {
        currentUser = null;
    }

    public static SessionCache getCurrentSession() {
        if (currentUser == null) {
            throw new IllegalStateException("No active session");
        }
        return CacheRepository.getSession(currentUser)
                .orElseThrow(() -> new IllegalStateException("Session expired"));
    }
}