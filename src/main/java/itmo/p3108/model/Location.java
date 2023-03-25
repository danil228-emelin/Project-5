package itmo.p3108.model;

import itmo.p3108.util.annotation.BuilderClass;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@BuilderClass

@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
    @XmlElement
    private double locationX;
    @XmlElement
    private Float locationY;
    @XmlElement
    private float locationZ;
    @XmlElement
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

    public static class LocationBuilder {
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

        public LocationBuilder name(String name) {
            this.name = name;
            return this;
        }


        public LocationBuilder locationX(String x) {
            this.x = Double.parseDouble(x);
            return this;
        }

        public LocationBuilder locationY(String y) {
            this.y = Float.valueOf(y);
            return this;
        }

        public LocationBuilder locationZ(String z) {
            this.z = Float.parseFloat(z);
            return this;
        }

        public Location build() {
            return new Location(this);
        }
    }
}
