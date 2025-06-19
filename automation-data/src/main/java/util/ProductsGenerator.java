package util;

import DTO.ProductInfo;
import models.ProductCache;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

import static services.CartService.addToCart;
import static variables.ProductVariables.ALL_PRODUCTS;

public class ProductsGenerator {
    private static final Logger log = LoggerFactory.getLogger(ProductsGenerator.class);

    public static void loadProducts() {
        ALL_PRODUCTS.clear();
        log.info("Loading products.....");
        try {
            String url = "https://automationexercise.com";
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".overlay-content > h2, .overlay-content > p");

            for (int i = 0; i < elements.size() - 1; i += 2) {
                String priceText = elements.get(i).text();
                String name = elements.get(i + 1).text();

                int price = Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
                ALL_PRODUCTS.add(ProductInfo.builder().name(name).price(price).build());
            }
        log.info("Downloaded {} products.", ALL_PRODUCTS.size());
        } catch (IOException e) {
            System.err.println("Failed to load product data: " + e.getMessage());
        }
    }

    public static Map<String, Integer> generateProducts(int count) {
        List<ProductInfo> products = ALL_PRODUCTS;
        Map<String, Integer> savedProducts = new HashMap<>();


        Random random = new Random();
        if (products.isEmpty()) {
            log.info("No products found");
            return savedProducts;
        }

        if (count > products.size()) {
            throw new IllegalArgumentException("Requested more items than available.");
        }

        Set<Integer> indexSet = new HashSet<>();
        while (indexSet.size() < count) {
            indexSet.add(random.nextInt(products.size()));
        }

        for (Integer index : indexSet) {
            ProductInfo product = products.get(index);

            int quantity = random.nextInt(5) + 1;

            savedProducts.put(product.getName(), quantity);

            addToCart(ProductCache.builder()
                    .productName(product.getName())
                    .price(product.getPrice())
                    .quantity(quantity)
                    .build());

        }

        return savedProducts;
    }

}

