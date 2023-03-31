package itmo.p3108.model;


import itmo.p3108.adapter.LocalDateAdapter;
import itmo.p3108.adapter.ZonedDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * class Person,objects of this class are  elements of  @see {@link itmo.p3108.util.CollectionController}
 */
@Data
@Builder
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @XmlElement(name = "Id")
    private Long personId;
    @XmlElement(name = "name")
    private String personName;
    @XmlElement(name = "coordinates")
    private Coordinates coordinates;
    @XmlJavaTypeAdapter(ZonedDateAdapter.class)
    @XmlElement(name = "creationDate")
    private java.time.ZonedDateTime personCreationDate;
    private Double personHeight;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "birthday")
    private java.time.LocalDate personBirthday;
    @XmlElement(name = "eyeColor")
    private Color personEyeColor;
    @XmlElement(name = "nationality")
    private Country personNationality;
    @XmlElement(name = "location")
    private Location location;


    @Override
    public int hashCode() {
        int result = personId.hashCode();
        result = 31 * result + personName.hashCode();
        result = 31 * result + coordinates.hashCode();
        result = 31 * result + personCreationDate.hashCode();
        result = 31 * result + personHeight.hashCode();
        result = 31 * result + personBirthday.hashCode();
        result = 31 * result + personEyeColor.hashCode();
        result = 31 * result + personNationality.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Person person) {
            return this.personName.equals(person.personName) && this.coordinates.equals(person.coordinates) && this.personHeight.equals(person.personHeight) && this.personBirthday.equals(person.personBirthday) && this.personEyeColor.equals(person.personEyeColor) && this.personNationality.equals(person.personNationality) && this.location.equals(person.location);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Person{" + "\n" + "personId=" + personId + "\n" + "personName='" + personName + '\'' + "\n" + "coordinates=" + coordinates + "\n" + "personCreationDate=" + personCreationDate + "\n" + "personHeight=" + personHeight + "\n" + "personBirthday=" + personBirthday + "\n" + "personEyeColor=" + personEyeColor + "\n" + "personNationality=" + personNationality + "\n" + "location=" + location + "\n" + '}';
    }


}
