package itmo.p3108.model;


import java.util.Arrays;

public enum Color {
    GREEN("1)green"),
    BLUE("2)blue"),
    YELLOW("3)yellow"),
    WHITE("4)white"),
    BROWN("5)brown");
    private String name;

    Color(String name) {
        this.name = name;
    }

    public static Color newValue(String str) {
        for (Color color : Color.values()) {
            if (color.getName().startsWith(str))
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