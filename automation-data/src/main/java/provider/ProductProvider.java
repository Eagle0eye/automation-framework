package provider;

import org.testng.annotations.DataProvider;

import variables.ProductVariables;

import java.util.*;
import java.util.Map.Entry;

import static util.ProductsGenerator.generateProducts;


public class ProductProvider {
    @DataProvider(name = "generatedProducts")
    public Object[][] getGeneratedProducts() {
        Map<String, Integer> products = generateProducts(5);
        return new Object[][] { { products } };
    }


    @DataProvider(name = "generatedEmptyProducts")
    public Object[][] dataProvider() {
        Map<String, Integer> products = new HashMap<>();
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
