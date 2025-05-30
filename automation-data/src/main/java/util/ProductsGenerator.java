package util;

import DTO.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ProductsGenerator {
    private static final Logger log = LoggerFactory.getLogger(ProductsGenerator.class);

    public static Map<String, Integer> generateProducts(List<ProductInfo> products) {
        Map<String, Integer> productMap = new HashMap<>();

        if (products == null || products.isEmpty()) {
            log.info("No products found");
            return productMap;
        }


        List<ProductInfo> shuffledProducts = new ArrayList<>(products);
        Collections.shuffle(shuffledProducts);
        Random random = new Random();
        int upperLimit = Math.min(10, shuffledProducts.size());
        int selectedCount = random.nextInt(upperLimit) + 1;

        for (int i = 0; i < selectedCount; i++) {
            ProductInfo product = shuffledProducts.get(i);
            int quantity = random.nextInt(5) + 1;
            productMap.put(product.getProductName(), quantity);
        }

        return productMap;
    }
}
