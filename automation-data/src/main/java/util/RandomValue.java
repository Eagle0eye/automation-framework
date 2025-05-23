package util;

import java.util.List;
import java.util.Random;

public class RandomValue<T> {
    private final List<T> values;
    private final Random rand;

    public RandomValue(List<T> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("values cannot be null or empty");
        }
        this.values = values;
        this.rand = new Random();
    }

    public T getRandomValue() {
        return values.get(rand.nextInt(values.size()));
    }
}
