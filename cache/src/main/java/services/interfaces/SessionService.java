package services.interfaces;

import models.SessionCache;

public interface SessionService {
    SessionCache currentSession();
    void createSession(SessionCache value);
    void updateSession(String email,SessionCache value);
    void closeSession();
}
