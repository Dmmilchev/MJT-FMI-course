package bg.sofia.uni.fmi.mjt.gym.member;

import java.lang.Math;
public record Address(double longitude, double latitude) {
    public double getDistanceTo(Address other) {
        return Math.sqrt(Math.pow((Math.abs(this.longitude - other.longitude)), 2) +
                        Math.pow((Math.abs(this.latitude - other.latitude)), 2));
    }
}
