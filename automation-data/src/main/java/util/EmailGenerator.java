package util;

import DTO.Login;
import mongo.AuthRepository;

public class EmailGenerator {
    private static AuthRepository authRepository= new AuthRepository();
    private static RandomValue<Login> validCredentials = new RandomValue<>(authRepository.getAllUsers());

    public static Login generateUser() {
        return validCredentials.getRandomValue();
    }

}
