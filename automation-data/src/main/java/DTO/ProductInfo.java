package DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ProductInfo {
    private String name;
    private int price;
    private int quantity;
}
