package itmo.p3108.model;


import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.adapter.ZonedDateAdapter;
import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * class Person,objects of this class are  elements of  @see {@link itmo.p3108.util.CollectionController}
 */
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Person person) {
            return
                    this.name.equals(person.name) &&
                            this.coordinates.equals(person.coordinates) &&
                            this.height == person.height &&
                            this.birthday.equals(person.birthday) &&
                            this.eyeColor.equals(person.eyeColor) &&
                            this.nationality.equals(person.nationality) &&
                            this.location.equals(person.location);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name=%s, height=%.3f, birthday=%s, eyeColor=%s, nationality=%s}",
                id, name, height, birthday, eyeColor, nationality);

    }
}

