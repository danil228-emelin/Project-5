package itmo.p3108.model;

import itmo.p3108.util.Builder;
import itmo.p3108.util.annotation.BuilderClass;
import itmo.p3108.util.annotation.ParsingMethod;
import lombok.Data;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * class Coordinates using as coordinates for  @see {@link Person}
 */
@Data
@BuilderClass(builderClass = Coordinates.CoordinatesBuilder.class)

@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @XmlElement
    @NonNull
    private Integer coordinatesX;
    @XmlElement
    @NonNull
    private Float coordinatesY;

    private Coordinates(CoordinatesBuilder coordinatesBuilder) {
        coordinatesX = coordinatesBuilder.x;
        coordinatesY = coordinatesBuilder.y;
    }

    public static CoordinatesBuilder builder() {
        return new CoordinatesBuilder();
    }

    @Override
    public String toString() {
        return String.format("Coordinates{x=%d, y= %f}", coordinatesX, coordinatesY);
    }

    public static class CoordinatesBuilder implements Builder {
        private Integer x;
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

        @ParsingMethod
        public CoordinatesBuilder coordinatesX(String x) {
            this.x = Integer.parseInt(x);
            return this;
        }

        @ParsingMethod
        public CoordinatesBuilder coordinatesY(String y) {
            this.y = Float.valueOf(y);
            return this;
        }

        @Override
        public String toString() {
            return "CoordinatesBuilder{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Coordinates build() {
            return new Coordinates(this);
        }
    }

}
