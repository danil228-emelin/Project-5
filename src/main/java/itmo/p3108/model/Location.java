package itmo.p3108.model;

import itmo.p3108.util.Builder;
import itmo.p3108.util.annotation.ParsingMethod;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "location")
public class Location {
    private Double locationX;
    private Float locationY;
    private Float locationZ;
    private String locationName;

    public Location() {
    }

    public Location(Double locationX, Float locationY, Float locationZ, String locationName) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.locationName = locationName;
    }

    private Location(LocationBuilder locationBuilder) {
        locationX = locationBuilder.x;
        locationY = locationBuilder.y;
        locationZ = locationBuilder.z;
        locationName = locationBuilder.name;
    }

    public static LocationBuilder builder() {
        return new LocationBuilder();
    }

    @XmlElement(name = "locationX")


    public Double getLocationX() {
        return locationX;
    }

    public void setLocationX(Double locationX) {
        this.locationX = locationX;
    }

    @XmlElement(name = "locationY")
    public Float getLocationY() {
        return locationY;
    }

    public void setLocationY(Float locationY) {
        this.locationY = locationY;
    }

    @XmlElement(name = "locationZ")
    public Float getLocationZ() {
        return locationZ;
    }

    public void setLocationZ(Float locationZ) {
        this.locationZ = locationZ;
    }

    @XmlElement(name = "name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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
