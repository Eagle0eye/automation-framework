package services.interfaces;

import models.UserInfo;

import java.util.Optional;

public interface UserProfileService {
    void login(String email, String userInfo);
    void logout();
}
