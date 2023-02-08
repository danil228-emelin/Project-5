package itmo.p3108.classes;

import lombok.Builder;

@Builder
public class Person {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private double height;
    private java.util.Date birthday;
    private Color eyeColor;
    private Country nationality;
    private Location location;
}
