package itmo.p3108.model;

import java.util.Arrays;

public enum Country {
    RUSSIA("1)russia"), FRANCE("2)france"), SPAIN("3)spain"), NORTH_KOREA("4)north_korea");
    private final String name;

    Country(String s) {
        name = s;
    }

    public static Country newValue(String str) {

        for (Country country : Country.values()) {
            if (country.getName().startsWith(str)) {
                return country;
            }
        }
        return null;
    }

    public static String[] countries() {
        return Arrays.stream(Country.values()).map(Country::getName).toArray(String[]::new);
    }

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
