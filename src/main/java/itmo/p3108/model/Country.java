package itmo.p3108.model;

import com.sun.istack.Nullable;

import java.util.Arrays;
import java.util.Optional;

/**
 * class Country using to describe current location of  @see {@link Person}
 */
public enum Country {
    RUSSIA("1)russia"), FRANCE("2)france"), SPAIN("3)spain"), NORTH_KOREA("4)north_korea");
    private final String name;

    Country(String s) {
        name = s;
    }

    @Nullable
    public static Optional<Country> newValue(String str) {

        for (Country country : Country.values()) {
            if (country.getName().startsWith(str)) {
                return Optional.of(country);
            }
        }
        return Optional.empty();
    }

    /**
     * @return all constants converted to String
     */
    public static String[] countries() {
        return Arrays.stream(Country.values()).map(Country::getName).toArray(String[]::new);
    }

    /**
     * @param test by id check whether enum constant exist or not
     */
    public static boolean isPresent(String test) {
        for (Country country : Country.values()) {
            if (country.getName().substring(2).equals(test.toLowerCase())) {

                return true;
            }

        }
        return false;
    }

    public String getName() {
        return name;
    }


}
