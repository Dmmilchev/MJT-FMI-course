package bg.sofia.uni.fmi.mjt.itinerary;

import java.math.BigDecimal;
import java.util.Comparator;

import bg.sofia.uni.fmi.mjt.itinerary.vehicle.VehicleType;

public record Journey(VehicleType vehicleType, City from, City to, BigDecimal price) implements Comparable<Journey> {

    public BigDecimal getTotalPrice() {
        BigDecimal greenTaxCalculated = vehicleType.getGreenTax().multiply(price);
        return greenTaxCalculated.add(price);
    }

    @Override
    public int compareTo(Journey o) {
        return this.getTotalPrice().compareTo(o.getTotalPrice());
    }
}