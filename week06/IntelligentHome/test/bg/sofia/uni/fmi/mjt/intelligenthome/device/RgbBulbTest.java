package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RgbBulbTest {
    private static final int RANDOM_POWER_CONSUMPTION = 10;
    private RgbBulb bulb;
    @BeforeEach
    void initializeThermostat() {
        this.bulb = new RgbBulb("Bulb", RANDOM_POWER_CONSUMPTION, LocalDateTime.now());
    }

    @Test
    @Order(1) //testing the unique ID means that this test should run first
    void testGetIdShouldEndUpWithCorrectUniqueNumberDevice() {
        assertTrue(bulb.getId().endsWith("0"), "ID has to end with 0 if the device is the first initialized");

        RgbBulb bulb1 = new RgbBulb("Bulb1", RANDOM_POWER_CONSUMPTION, LocalDateTime.now());
        assertTrue(bulb1.getId().endsWith("1"), "ID has to end with 1 if the device is the second initialized");
    }

    @Test
    void testGetIdShouldStartWithShortTypeName() {
        assertTrue(bulb.getId().startsWith("BLB"), "ID has to start with BLB if the device is thermostat");
    }

    @Test
    void testGetIdIdShouldHaveNameInTheMiddle() {
        String[] parts = bulb.getId().split("-");
        assertEquals(bulb.getName(), parts[1]);
    }

}
