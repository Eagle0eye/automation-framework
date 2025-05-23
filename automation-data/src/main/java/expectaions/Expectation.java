package expectaions;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Expectation {
    private int statusCode;
    private String message;
}
