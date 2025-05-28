package DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfo {
    private String productId;
    private String productName;
    private String productPrice;
}
