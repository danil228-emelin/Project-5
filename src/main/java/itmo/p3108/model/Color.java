package itmo.p3108.model;


import com.sun.istack.Nullable;

import java.util.Arrays;
import java.util.Optional;

/**
 * class Colour using as  @see {@link Person} eye's color
 */
public enum Color {
    GREEN("1)green"),
    BLUE("2)blue"),
    YELLOW("3)yellow"),
    WHITE("4)white"),
    BROWN("5)brown");
    private final String name;

    Color(String name) {
        this.name = name;
    }

    @Nullable
    public static Optional<Color> newValue(String str) {
        for (Color color : Color.values()) {
            if (color.getName().startsWith(str))
                return Optional.of(color);
        }
        return Optional.empty();

    }

    /**
     * @param test by id check whether enum constant exist or not
     */
    public static boolean isPresent(String test) {
        for (Color color : Color.values()) {
            if (color.getName().substring(2).equals(test.toLowerCase())) {

                return true;
            }

        }
        return false;
    }

    /**
     * @return all constants converted to String
     */
    public static String[] colors() {
        return Arrays.stream(Color.values()).map(Color::getName).toArray(String[]::new);
    }

    public String getName() {
        return name;
    }
}
