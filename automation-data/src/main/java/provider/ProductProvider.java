package provider;

import org.testng.annotations.DataProvider;

import variables.ProductVariables;

import java.util.*;
import java.util.Map.Entry;

import static util.ProductsGenerator.generateProducts;


public class ProductProvider {
    @DataProvider(name = "AllProducts")
    public Map<String, Integer>[] dataProvider() {
        return new Map[]{ generateProducts(5) };
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
