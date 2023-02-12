package itmo.p3108.model;

import java.util.Arrays;

public enum Country {
    RUSSIA("russia"), FRANCE("france"), SPAIN("spain"), NORTH_KOREA("north_korea");
    private String name;

    Country(String s) {
        name = s;
    }

    public static Country newValue(String str) {
        for (Country color : Country.values()) {
            if (color.name().equalsIgnoreCase(str))
                return color;
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
