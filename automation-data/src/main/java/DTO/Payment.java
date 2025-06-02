package DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {
    private String cardName;
    private String cardNumber;
    private String cvc;
    private String month;
    private String year;

}
