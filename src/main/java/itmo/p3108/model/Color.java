package itmo.p3108.model;


import java.util.Arrays;

public enum Color {
    GREEN("green"),
    BLUE("blue"),
    YELLOW("yellow"),
    WHITE("white"),
    BROWN("brown");
    private String name;

    Color(String name) {
        this.name = name;
    }

    public static Color newValue(String str) {
        for (Color color : Color.values()) {
            if (color.name().equalsIgnoreCase(str))
                return color;
        }
        return null;
    }

    public static String[] colors() {
        return Arrays.stream(Color.values()).map(Color::getName).toArray(String[]::new);
    }

    public String getName() {
        return name;
    }
}