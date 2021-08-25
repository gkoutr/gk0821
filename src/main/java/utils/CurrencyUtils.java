package utils;

import java.util.Locale;

public class CurrencyUtils {

    /**
     * Rounds float values to 2 decimal places using String.format.
     *
     * @param value
     * @return
     */
    public static float formatFloat(float value) {
        return Float.valueOf(String.format(Locale.getDefault(), "%.2f", value));
    }
}
