package itmo.p3108.model;

import java.util.Arrays;

public enum Country {
    RUSSIA("1)Russia"), FRANCE("2)France"), SPAIN("3)Spain"), NORTH_KOREA("4)North_Korea");
    private String name;

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

    public String getName() {
        return name;
    }


}
