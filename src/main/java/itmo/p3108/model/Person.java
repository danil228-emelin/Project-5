package itmo.p3108.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", height=" + height +
                ", birthday=" + birthday +
                ", eyeColor=" + eyeColor +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }
}
