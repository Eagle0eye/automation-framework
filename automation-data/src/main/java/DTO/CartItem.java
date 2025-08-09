package DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {
    private String productName;
    private int quantity;
    private int price;
   private int totalPrice;
}
