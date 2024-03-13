package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WiFiThermostatTest {

    private static final int RANDOM_POWER_CONSUMPTION = 10;
    private WiFiThermostat thermostat;
    @BeforeEach
    void initializeThermostat() {
        this.thermostat = new WiFiThermostat("Thermostat", RANDOM_POWER_CONSUMPTION, LocalDateTime.now());
    }

    @Test
    @Order(1) //testing the unique ID means that this test should run first
    void testGetIdShouldEndUpWithCorrectUniqueNumberDevice() {
        assertTrue(thermostat.getId().endsWith("0"), "ID has to end with 0 if the device is the first initialized");

        WiFiThermostat thermostat1 = new WiFiThermostat("Thermostat1", 10, LocalDateTime.now());
        assertTrue(thermostat1.getId().endsWith("1"), "ID has to end with 1 if the device is the second initialized");
    }

    @Test
    void testGetIdShouldStartWithShortTypeName() {
        assertTrue(thermostat.getId().startsWith("TMST"), "ID has to start with TMST if the defice is thermostat");
    }

    @Test
    void testGetIdIdShouldHaveNameInTheMiddle() {
        String[] parts = thermostat.getId().split("-");
        assertEquals(thermostat.getName(), parts[1]);
    }

}
