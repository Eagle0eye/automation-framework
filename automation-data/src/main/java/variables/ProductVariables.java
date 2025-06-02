package variables;

import DTO.ProductInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductVariables {
    public static final Map<String, Integer> SEARCH_MAP = Map.of(
            "top", 14,
            "dress", 9,
            "jeans", 3,
            "tshirt", 6
    );

    public static List<ProductInfo> ALL_PRODUCTS = new ArrayList<>();
}
