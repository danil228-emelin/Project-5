package itmo.p3108.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Builder;
import lombok.Data;

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
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
