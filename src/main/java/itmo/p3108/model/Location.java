package itmo.p3108.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;
import lombok.Data;

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
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}