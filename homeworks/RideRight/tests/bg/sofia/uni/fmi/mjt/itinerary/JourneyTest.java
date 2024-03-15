package bg.sofia.uni.fmi.mjt.itinerary;

import bg.sofia.uni.fmi.mjt.itinerary.vehicle.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class JourneyTest {
    private City from;
    private City to;

    @BeforeEach
    void initializeCities() {
        this.from = mock();
        this.to = mock();
    }

    @Test
    void testGetTotalPriceShouldReturnZeroIfPriceIsZero() {
        Journey journey = new Journey(VehicleType.BUS, from, to, new BigDecimal("0"));
        assertEquals(new BigDecimal("0.0"), journey.getTotalPrice(),
                "If price is zero, total price should be zero");
    }

    @Test
    void testGetTotalPriceShouldReturnEqualToPriceIfVehicleTypeIsTrain() {
        Journey journey = new Journey(VehicleType.TRAIN, from, to, new BigDecimal("1000"));
        assertEquals(new BigDecimal("1000"), journey.getTotalPrice(),
                "If price is 1000 and vehicle type is train, total price must be 1000");
    }

    @Test
    void testGetTotalPriceShouldReturnTenPercentMoreIfVehicleTypeIsBus() {
        Journey journey = new Journey(VehicleType.BUS, from, to, new BigDecimal("1000"));
        assertEquals(new BigDecimal("1100.0"), journey.getTotalPrice(),
                "If price is 1000 and vehicle type is bus, total price must be 1100");
    }

    @Test
    void testGetTotalPriceShouldReturnTwentyFivePercentMoreIfVehicleTypeIsBus() {
        Journey journey = new Journey(VehicleType.PLANE, from, to, new BigDecimal("1000"));
        assertEquals(new BigDecimal("1250.00"), journey.getTotalPrice(),
                "If price is 1000 and vehicle type is bus, total price must be 1250");
    }

    @Test
    void testCompareToSameJourneyMustBeEqual() {
        Journey journey = new Journey(VehicleType.PLANE, from, to, new BigDecimal("1000"));
        assertEquals(0, journey.compareTo(journey));
    }

    @Test
    void testCompareToSamePriceAndTypeMustBeEqual() {
        Journey journey1 = new Journey(VehicleType.PLANE, from, to, new BigDecimal("1000"));
        Journey journey2 = new Journey(VehicleType.PLANE, from, to, new BigDecimal("1000"));
        assertEquals(0, journey1.compareTo(journey2));
    }

    @Test
    void testCompareToMoreExpensiveJourneyMustBeLesser() {
        Journey journey1 = new Journey(VehicleType.PLANE, from, to, new BigDecimal("100"));
        Journey journey2 = new Journey(VehicleType.PLANE, from, to, new BigDecimal("1000"));

        assertTrue(0 < journey2.compareTo(journey1));
    }

    @Test
    void testCompareToCheaperMustBeGreater() {
        Journey journey1 = new Journey(VehicleType.PLANE, from, to, new BigDecimal("100"));
        Journey journey2 = new Journey(VehicleType.PLANE, from, to, new BigDecimal("1000"));

        assertTrue( 0 > journey1.compareTo(journey2));
    }
}
