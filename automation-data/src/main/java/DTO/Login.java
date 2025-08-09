package DTO;

import expectaions.Expectation;
import lombok.Builder;
import lombok.Data;

@Data @Builder( toBuilder = true)
public class Login {
    private String email;
    private String password;
    private PersonalInfo personalInfo;
    private Expectation expectation;

    @Override
    public String toString() {
        return personalInfo.getDetails(email);
    }
}
