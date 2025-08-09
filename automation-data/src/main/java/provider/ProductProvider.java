package provider;

import DTO.ProductInfo;
import models.ProductCache;
import org.testng.annotations.DataProvider;

import variables.ProductVariables;

import java.util.*;
import java.util.Map.Entry;

import static util.ProductsGenerator.generateProducts;
import static util.ProductsGenerator.generateRecommendedProducts;


public class ProductProvider {

    @DataProvider(name = "Products")
    public Object[][] getGeneratedProducts() {
        Set<ProductInfo> products = generateProducts(5);
        return new Object[][] { { products } };
    }

    @DataProvider(name = "RecommendedProducts")
    public Object[][] getGenerateRecommendedProducts() {
        Set<ProductInfo> products = generateRecommendedProducts(5);
        return new Object[][] { { products } };
    }

    @DataProvider(name = "EmptyProducts")
    public Object[][] dataProvider() {
        Set<ProductCache> products = new HashSet<>();
        return new Object[][] { { products } };
    }

    @DataProvider(name = "SearchValues")
    public static Object[][] getRandomSearchValue() {
        List<Entry<String, Integer>> entries = new ArrayList<>(ProductVariables.SEARCH_MAP.entrySet());
        Entry<String, Integer> randomEntry = entries.get(new Random().nextInt(entries.size()));

        return new Object[][] {
                { randomEntry.getKey(), randomEntry.getValue() }
        };
    }
}
