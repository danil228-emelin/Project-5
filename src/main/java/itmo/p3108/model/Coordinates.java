package itmo.p3108.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * class Coordinates using as coordinates for  @see {@link Person}
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @XmlElement
    private int coordinateX;
    @XmlElement
    private Float coordinateY;

    private Coordinates(CoordinatesBuilder coordinatesBuilder) {
        coordinateX = coordinatesBuilder.x;
        coordinateY = coordinatesBuilder.y;
    }

    public static CoordinatesBuilder builder() {
        return new CoordinatesBuilder();
    }

    @Override
    public String toString() {
        return String.format("Coordinates{x=%d, y= %f}", coordinateX, coordinateY);
    }

    public static class CoordinatesBuilder {
        private int x;
        private Float y;

        private CoordinatesBuilder() {

        }

        public CoordinatesBuilder setX(int x) {
            this.x = x;
            return this;
        }

        public CoordinatesBuilder setY(Float y) {
            this.y = y;
            return this;
        }

        public CoordinatesBuilder x(String x) {
            this.x = Integer.parseInt(x);
            return this;
        }

        public CoordinatesBuilder y(String y) {
            this.y = Float.valueOf(y);
            return this;
        }

        public Coordinates build() {
            return new Coordinates(this);
        }
    }

}
