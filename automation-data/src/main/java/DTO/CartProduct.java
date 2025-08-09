package DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder( toBuilder = true)
public class CartProduct {
    private String productName;
    private int quantity;

    @Builder.Default
    private int price = 0;

    public int totalPrice() {
        return price*quantity;
    }
}
