package itmo.p3108.model;

import lombok.Builder;

@Builder
public class Coordinates {
    private int x;
    private Float y;

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
