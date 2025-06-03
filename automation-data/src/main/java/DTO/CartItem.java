package DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {
    private String productName;
    private String quantity;
    private String price;
    private String totalPrice;
}
