package services;

import context.UserContext;
import models.ProductCache;
import models.SessionCache;
import models.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.interfaces.UserProfileService;

import java.util.HashSet;

public class UserProfileServiceImpl implements UserProfileService {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);
    private final SessionServiceImpl sessionService = new SessionServiceImpl();

    @Override
    public void login(String email, String userInfo) {
        // Set current user in context
        UserContext.CurrentUser(email);

        // Merge anonymous session if exists
        sessionService.mergeAnonymousSessionToUser(email);

        // Create or update user session
        SessionCache session = sessionService.currentSession();
        if (session == null) {
            session = SessionCache.builder()
                    .email(email)
                    .accountInfo(userInfo)
                    .cartItems(new HashSet<>())
                    .build();
            sessionService.createSession(session);
        }

        logger.info("User {} logged in successfully", email);
    }

    @Override
    public void logout() {
        String email = UserContext.current_user();
        // Keep the session in cache, just clear the context
        UserContext.clear();
        logger.info("User {} logged out", email);
    }
}