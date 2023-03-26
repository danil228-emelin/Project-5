package itmo.p3108.model;

import itmo.p3108.util.Builder;
import itmo.p3108.util.annotation.BuilderClass;
import itmo.p3108.util.annotation.ParsingMethod;
import lombok.Data;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@BuilderClass(builderClass = Location.LocationBuilder.class)

@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
    @XmlElement
    @NonNull
    private Double locationX;
    @XmlElement
    @NonNull
    private Float locationY;
    @XmlElement
    @NonNull
    private Float locationZ;
    @XmlElement
    @NonNull
    private String locationName;

    private Location(LocationBuilder locationBuilder) {
        locationX = locationBuilder.x;
        locationY = locationBuilder.y;
        locationZ = locationBuilder.z;
        locationName = locationBuilder.name;
    }

    public static LocationBuilder builder() {
        return new LocationBuilder();
    }

    @Override
    public String toString() {
        return String.format("Location{x=%f, y=%f, z=%f ,name=%s}", locationX, locationY, locationZ, locationName);
    }

    public static class LocationBuilder implements Builder {
        private double x;
        private Float y;
        private float z;
        private String name;

        private LocationBuilder() {
        }

        public LocationBuilder locationX(double x) {
            this.x = x;
            return this;
        }

        public LocationBuilder locationY(Float y) {
            this.y = y;
            return this;
        }

        public LocationBuilder locationZ(float z) {
            this.z = z;
            return this;
        }

        @ParsingMethod
        public LocationBuilder locationName(String name) {
            this.name = name;
            return this;
        }

        @ParsingMethod
        public LocationBuilder locationX(String x) {
            this.x = Double.parseDouble(x);
            return this;
        }

        @ParsingMethod
        public LocationBuilder locationY(String y) {
            this.y = Float.valueOf(y);
            return this;
        }

        @ParsingMethod
        public LocationBuilder locationZ(String z) {
            this.z = Float.parseFloat(z);
            return this;
        }

        @Override
        public String toString() {
            return "LocationBuilder{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", name='" + name + '\'' +
                    '}';
        }

        public Location build() {
            return new Location(this);
        }
    }
}
