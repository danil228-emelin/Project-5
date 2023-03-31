package itmo.p3108.model;


import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.adapter.ZonedDateAdapter;
import itmo.p3108.util.Builder;
import itmo.p3108.util.annotation.BuilderClass;
import itmo.p3108.util.annotation.ParsingMethod;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class Person,objects of this class are  elements of  @see {@link itmo.p3108.util.CollectionController}
 */
@BuilderClass(builderClass = Person.PersonBuilder.class)
@XmlRootElement(name = "person")
public class Person {
    private Long personId;
    private String personName;
    private Coordinates coordinates;
    private java.time.ZonedDateTime personCreationDate;
    private Double personHeight;

    private java.time.LocalDate personBirthday;
    private Color personEyeColor;
    private Country personNationality;
    private Location location;

    public Person() {
    }

    public Person(Long personId, String personName, Coordinates coordinates, ZonedDateTime personCreationDate, Double personHeight, LocalDate personBirthday, Color personEyeColor, Country personNationality, Location location) {
        this.personId = personId;
        this.personName = personName;
        this.coordinates = coordinates;
        this.personCreationDate = personCreationDate;
        this.personHeight = personHeight;
        this.personBirthday = personBirthday;
        this.personEyeColor = personEyeColor;
        this.personNationality = personNationality;
        this.location = location;
    }

    private Person(PersonBuilder personBuilder) {
        personId = personBuilder.id;
        personName = personBuilder.name;
        coordinates = personBuilder.coordinates;
        personCreationDate = personBuilder.creationDate;
        personHeight = personBuilder.height;
        personBirthday = personBuilder.birthday;
        personEyeColor = personBuilder.eyeColor;
        personNationality = personBuilder.nationality;
        location = personBuilder.location;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    @XmlElement(name = "id")
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @XmlElement(name = "name")
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @XmlElement(name = "coordinates")
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @XmlElement(name = "creationDate")
    @XmlJavaTypeAdapter(ZonedDateAdapter.class)

    public ZonedDateTime getPersonCreationDate() {
        return personCreationDate;
    }

    public void setPersonCreationDate(ZonedDateTime personCreationDate) {
        this.personCreationDate = personCreationDate;
    }

    @XmlElement(name = "height")
    public Double getPersonHeight() {
        return personHeight;
    }

    public void setPersonHeight(Double personHeight) {
        this.personHeight = personHeight;
    }

    @XmlElement(name = "birthday")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(LocalDate personBirthday) {
        this.personBirthday = personBirthday;
    }

    @XmlElement(name = "eyeColor")
    public Color getPersonEyeColor() {
        return personEyeColor;
    }

    public void setPersonEyeColor(Color personEyeColor) {
        this.personEyeColor = personEyeColor;
    }

    @XmlElement(name = "nationality")
    public Country getPersonNationality() {
        return personNationality;
    }

    public void setPersonNationality(Country personNationality) {
        this.personNationality = personNationality;
    }

    @XmlElement(name = "location")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
        return "Person{" + "\n" +
                "personId=" + personId + "\n" +
                "personName='" + personName + '\'' + "\n" +
                "coordinates=" + coordinates + "\n" +
                "personCreationDate=" + personCreationDate + "\n" +
                "personHeight=" + personHeight + "\n" +
                "personBirthday=" + personBirthday + "\n" +
                "personEyeColor=" + personEyeColor + "\n" +
                "personNationality=" + personNationality + "\n" +
                "location=" + location + "\n" +
                '}';
    }

    public static class PersonBuilder implements Builder {
        private Long id;
        private String name;
        private Coordinates coordinates;
        private Double height;
        private java.time.LocalDate birthday;
        private Color eyeColor;
        private Country nationality;
        private Location location;
        private java.time.ZonedDateTime creationDate;

        private PersonBuilder() {
        }

        @ParsingMethod
        public PersonBuilder personId(String id) {
            this.id = Long.valueOf(id);
            return this;
        }

        @ParsingMethod

        public PersonBuilder personCreationDate(String date) {
            creationDate = ZonedDateTime.parse(date);
            return this;
        }

        public PersonBuilder personId(Long id) {
            this.id = id;
            return this;
        }

        @ParsingMethod
        public PersonBuilder personName(String name) {
            this.name = name;
            return this;
        }

        @ParsingMethod
        public PersonBuilder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        @ParsingMethod
        public PersonBuilder personHeight(String height) {
            this.height = Double.valueOf(height);
            return this;
        }

        public PersonBuilder personHeight(double height) {
            this.height = height;
            return this;
        }


        public PersonBuilder personBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        @ParsingMethod
        public PersonBuilder personBirthday(String birthday) {
            this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            return this;
        }

        public PersonBuilder personEyeColor(Color eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        @ParsingMethod
        public PersonBuilder personEyeColor(String eyeColor) {
            this.eyeColor = Color.valueOf(eyeColor);
            return this;
        }

        public PersonBuilder personNationality(Country nationality) {
            this.nationality = nationality;
            return this;
        }

        @ParsingMethod
        public PersonBuilder personNationality(String nationality) {
            this.nationality = Country.valueOf(nationality);
            return this;
        }

        @ParsingMethod
        public PersonBuilder location(Location location) {
            this.location = location;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

        @Override
        public String toString() {
            return "PersonBuilder{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", coordinates=" + coordinates +
                    ", height=" + height +
                    ", birthday=" + birthday +
                    ", eyeColor=" + eyeColor +
                    ", nationality=" + nationality +
                    ", location=" + location +
                    ", creationDate=" + creationDate +
                    '}';
        }
    }

}
