package itmo.p3108.model;

import itmo.p3108.util.Builder;
import itmo.p3108.util.annotation.BuilderClass;
import itmo.p3108.util.annotation.ParsingMethod;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * class Coordinates using as coordinates for  @see {@link Person}
 */
@BuilderClass(builderClass = Coordinates.CoordinatesBuilder.class)
@XmlRootElement(name = "coordinates")
public class Coordinates {
    private Integer coordinatesX;
    private Float coordinatesY;

    public Coordinates() {
    }

    public Coordinates(Integer coordinatesX, Float coordinatesY) {
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
    }

    private Coordinates(CoordinatesBuilder coordinatesBuilder) {
        coordinatesX = coordinatesBuilder.x;
        coordinatesY = coordinatesBuilder.y;
    }

    public static CoordinatesBuilder builder() {
        return new CoordinatesBuilder();
    }

    @XmlElement(name = "coordinateX")
    public Integer getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(Integer coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    @XmlElement(name = "coordinateY")
    public Float getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(Float coordinatesY) {
        this.coordinatesY = coordinatesY;
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
