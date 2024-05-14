package CVHandle;

import java.util.Random;

public class RandomDataGenerator {
    private static final Random random = new Random();

    // Generate random numbers within a specified range
    public static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    // Generate random strings of specified length
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = (char) ('a' + random.nextInt(26)); // Generate random lowercase letters
            sb.append(randomChar);
        }
        return sb.toString();
    }

    // Generate random data based on data type
    public static Object generateRandomData(Class<?> type) {
        if (type == Integer.class || type == int.class) {
            return generateRandomNumber(0, 100);
        } else if (type == String.class) {
            return generateRandomString(10);
        }
        // Add more data types as needed
        return null;
    }
}
