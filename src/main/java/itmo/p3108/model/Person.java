package itmo.p3108.model;


import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.adapter.ZonedDateAdapter;
import itmo.p3108.util.annotation.Parsing;
import lombok.Data;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class Person,objects of this class are  elements of  @see {@link itmo.p3108.util.CollectionController}
 */

@Data
@XmlAccessorType(XmlAccessType.FIELD)

public class Person {
    @XmlElement
    @NonNull
    private Long id;
    @XmlElement
    @NonNull
    private String name;
    @XmlElement
    @NonNull
    private Coordinates coordinates;
    @XmlJavaTypeAdapter(ZonedDateAdapter.class)
    private java.time.ZonedDateTime creationDate;
    @XmlElement
    @NonNull
    private Double height;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NonNull
    private java.time.LocalDate birthday;
    @XmlElement
    @NonNull
    private Color eyeColor;
    @XmlElement
    @NonNull
    private Country nationality;
    @XmlElement
    @NonNull
    private Location location;

    private Person(PersonBuilder personBuilder) {
        id = personBuilder.id;
        name = personBuilder.name;
        coordinates = personBuilder.coordinates;
        creationDate = ZonedDateTime.now();
        height = personBuilder.height;
        birthday = personBuilder.birthday;
        eyeColor = personBuilder.eyeColor;
        nationality = personBuilder.nationality;
        location = personBuilder.location;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Person person) {
            return this.name.equals(person.name) && this.coordinates.equals(person.coordinates) && this.height == person.height && this.birthday.equals(person.birthday) && this.eyeColor.equals(person.eyeColor) && this.nationality.equals(person.nationality) && this.location.equals(person.location);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name=%s, height=%.3f, birthday=%s, eyeColor=%s, nationality=%s}", id, name, height, birthday, eyeColor, nationality);

    }

    public static class PersonBuilder {

        private Long id;
        private String name;
        private Coordinates coordinates;
        private Double height;
        private java.time.LocalDate birthday;
        private Color eyeColor;
        private Country nationality;
        private Location location;

        private PersonBuilder() {
        }

        @Parsing
        public PersonBuilder id(String id) {
            this.id = Long.valueOf(id);
            return this;
        }

        public PersonBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @Parsing
        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        @Parsing
        public PersonBuilder height(String height) {
            this.height = Double.valueOf(height);
            return this;
        }

        public PersonBuilder height(double height) {
            this.height = height;
            return this;
        }


        public PersonBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        @Parsing
        public PersonBuilder birthday(String birthday) {
            this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return this;
        }

        public PersonBuilder eyeColor(Color eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        @Parsing
        public PersonBuilder eyeColor(String eyeColor) {
            this.eyeColor = Color.valueOf(eyeColor);
            return this;
        }

        public PersonBuilder nationality(Country nationality) {
            this.nationality = nationality;
            return this;
        }

        @Parsing
        public PersonBuilder nationality(String nationality) {
            this.nationality = Country.valueOf(nationality);
            return this;
        }


        public PersonBuilder location(Location location) {
            this.location = location;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

}
