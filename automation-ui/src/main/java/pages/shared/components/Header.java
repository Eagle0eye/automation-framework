package pages.shared.components;

import Cache.Cache;
import Cache.DTO.UserInfo;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Header {

    public static final Logger log = LoggerFactory.getLogger(Header.class);

    @Step("Get Logged username")
    public String getLoggedInUsername(){
        UserInfo currentUser = getUserInfo();
        if (currentUser == null) {
            log.info("No logged user");
            return null;
        }
        log.info("logged user: {}",currentUser.getName());
        return currentUser.getName();
    }
    private UserInfo getUserInfo() {
        return (UserInfo) Cache.get("test-account");
    }

}
