package util;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

public class Variables {
    private static final Faker faker = new Faker();
    // Credentials
    public static String validEmail="automation2@armyspy.com";
    public static String validPassword="auto@12345678";

    public static String invalidEmail = faker.internet().emailAddress();
    public static String invalidPassword = "00000000000";


    // User Information
    public static String register_name = faker.name().firstName();
    public static String register_email = faker.internet().emailAddress();
    public static String register_password = "auto@12345678";

    public static final short day = (short) ThreadLocalRandom.current().nextInt(1, 29);
    public static final short month = (short) ThreadLocalRandom.current().nextInt(1, 13);
    public static final short year = (short) ThreadLocalRandom.current().nextInt(1970, 2010);

    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();

    public static String address = faker.address().fullAddress();
    public static String address2 = faker.address().secondaryAddress();

    private static String country = faker.country().name();
    public static String state = faker.address().state();
    public static String city = faker.address().city();
    private static String zip = faker.address().zipCode();

    public static String phone = faker.phoneNumber().phoneNumber();



}
