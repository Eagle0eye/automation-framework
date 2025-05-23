package expectaions.api;

public class LoginExpected {
    public static final int OK = 200;
    public static final int NOT_FOUND = 404;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_SUPPORTED = 405;

    public static final String USER_EXISTS = "User exists!";
    public static final String USER_NOT_FOUND = "User not found!";
    public static final String MISSING_CREDENTIALS = "Bad request, email or password parameter is missing in POST request.";
    public static final String NOT_SUPPORTED_MESSAGE = "This request method is not supported.";
    public static final String DELETED_MESSAGE = "Account deleted!";
    public static final String UPDATED_MESSAGE = "User updated!";

}


