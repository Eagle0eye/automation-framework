package DTO;

import expectaions.Expectation;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Login {
    private String email;
    private String password;
    private Expectation expectation;
}
