package DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewProduct {
    private String name;
    private String email;
    private String message;
}
