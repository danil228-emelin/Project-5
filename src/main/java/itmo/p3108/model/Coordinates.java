package itmo.p3108.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * class Coordinates using as coordinates for  @see {@link Person}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @XmlElement(name = "x")
    private Integer coordinatesX;
    @XmlElement(name = "y")

    private Float coordinatesY;

    @Override
    public String toString() {
        return String.format("Coordinates{x=%d, y= %f}", coordinatesX, coordinatesY);
    }

}
