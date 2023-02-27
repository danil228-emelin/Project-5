package itmo.p3108.model;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Builder
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
    @XmlElement
    private double x;
    @XmlElement
    private Float y;
    @XmlElement
    private float z;
    @XmlElement
    private String name;

    @Override
    public String toString() {
        return String.format("Location{x=%f, y=%f, z=%f ,name=%s}", x, y, z, name);
    }
}