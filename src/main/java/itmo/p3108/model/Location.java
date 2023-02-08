package itmo.p3108.model;

import lombok.Builder;

@Builder
public class Location {
    private double x;
    private Float y;
    private float z;
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
