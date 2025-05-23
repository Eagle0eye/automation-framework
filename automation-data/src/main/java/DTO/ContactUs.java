package DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactUs {
    private String name;
    private String email;
    private String subject;
    private String message;
    private String path;
}
