package itmo.p3108.model;


import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.adapter.ZonedDateAdapter;
import itmo.p3108.util.annotation.BuilderClass;
import itmo.p3108.util.annotation.ParsingMethod;
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
    private Long personId;
    @XmlElement
    @NonNull
    private String personName;
    @XmlElement
    @NonNull
    private Coordinates coordinates;
    @XmlJavaTypeAdapter(ZonedDateAdapter.class)
    private java.time.ZonedDateTime personCreationDate;
    @XmlElement
    @NonNull
    private Double personHeight;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NonNull
    private java.time.LocalDate personBirthday;
    @XmlElement
    @NonNull
    private Color personEyeColor;
    @XmlElement
    @NonNull
    private Country personNationality;
    @XmlElement
    @NonNull
    private Location location;

    private Person(PersonBuilder personBuilder) {
        personId = personBuilder.id;
        personName = personBuilder.name;
        coordinates = personBuilder.coordinates;
        personCreationDate = ZonedDateTime.now();
        personHeight = personBuilder.height;
        personBirthday = personBuilder.birthday;
        personEyeColor = personBuilder.eyeColor;
        personNationality = personBuilder.nationality;
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
            return this.personName.equals(person.personName) && this.coordinates.equals(person.coordinates) && this.personHeight == person.personHeight && this.personBirthday.equals(person.personBirthday) && this.personEyeColor.equals(person.personEyeColor) && this.personNationality.equals(person.personNationality) && this.location.equals(person.location);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("{id=%d, name=%s, height=%.3f, birthday=%s, eyeColor=%s, nationality=%s}", personId, personName, personHeight, personBirthday, personEyeColor, personNationality);

    }

    @BuilderClass

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

        @ParsingMethod
        public PersonBuilder id(String id) {
            this.id = Long.valueOf(id);
            return this;
        }

        public PersonBuilder id(Long id) {
            this.id = id;
            return this;
        }

        @ParsingMethod
        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        @ParsingMethod
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

        @ParsingMethod
        public PersonBuilder birthday(String birthday) {
            this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return this;
        }

        public PersonBuilder eyeColor(Color eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        @ParsingMethod
        public PersonBuilder eyeColor(String eyeColor) {
            this.eyeColor = Color.valueOf(eyeColor);
            return this;
        }

        public PersonBuilder nationality(Country nationality) {
            this.nationality = nationality;
            return this;
        }

        @ParsingMethod
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
