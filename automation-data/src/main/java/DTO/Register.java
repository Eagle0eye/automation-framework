package DTO;

import lombok.Data;

@Data
public class User {
    private String name, email, password;

    private short day, month, year;

    private String firstname, lastname;

    private String company,address, address2;

    private String country, state, city, zipcode, phone;
}
