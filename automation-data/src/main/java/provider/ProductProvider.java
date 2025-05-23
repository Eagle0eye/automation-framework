package provider;

import org.testng.annotations.DataProvider;
import variables.ProductVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class ProductProvider {

    @DataProvider(name = "SearchValues")
    public static Object[][] getRandomSearchValue() {
        List<Entry<String, Integer>> entries = new ArrayList<>(ProductVariables.SEARCH_MAP.entrySet());
        Entry<String, Integer> randomEntry = entries.get(new Random().nextInt(entries.size()));

        return new Object[][] {
                { randomEntry.getKey(), randomEntry.getValue() }
        };
    }
}
