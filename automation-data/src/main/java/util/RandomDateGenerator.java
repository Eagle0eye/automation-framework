package util;

import java.time.LocalDate;

import java.util.concurrent.ThreadLocalRandom;

public class RandomDateGenerator {

    private static final int MIN_YEAR = 1995;
    private static final int MAX_YEAR = 2025;

    public static LocalDate generateRandomDateWithStaticYear()
    {
        int randomYear = ThreadLocalRandom.current().nextInt(MIN_YEAR, MAX_YEAR + 1);
        LocalDate start = LocalDate.of(randomYear, 1, 1);
        LocalDate end = LocalDate.of(randomYear, 12, 31);
        long days = end.toEpochDay() - start.toEpochDay();
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        return start.plusDays(randomDays);
    }
}
