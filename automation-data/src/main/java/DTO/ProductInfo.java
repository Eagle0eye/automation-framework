package DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfo {
    private String name;
    private int price;
}
