package itmo.p3108.model;


import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.adapter.ZonedDateAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@XmlAccessorType(XmlAccessType.FIELD)

public class Person {
    @XmlElement
    private Long id;
    @XmlElement
    private String name;
    @XmlElement
    private Coordinates coordinates;
    @XmlJavaTypeAdapter(ZonedDateAdapter.class)
    private java.time.ZonedDateTime creationDate;
    @XmlElement
    private double height;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private java.time.LocalDate birthday;
    @XmlElement
    private Color eyeColor;
    @XmlElement
    private Country nationality;
    @XmlElement
    private Location location;

    @Override
    public String toString() {
        return "{" +
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
