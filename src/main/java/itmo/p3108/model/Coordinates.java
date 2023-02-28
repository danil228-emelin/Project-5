package itmo.p3108.model;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * class Coordinates using as coordinates for  @see {@link Person}
 */
@Builder
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @XmlElement
    private int x;
    @XmlElement
    private Float y;

    @Override
    public String toString() {
        return String.format("Coordinates{x=%d, y= %f}", x, y);
    }
}
