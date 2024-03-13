package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AmazonAlexaTest {
    private static final int RANDOM_POWER_CONSUMPTION = 10;
    private AmazonAlexa amazonAlexa;
    @BeforeEach
    void initializeThermostat() {
        this.amazonAlexa= new AmazonAlexa("SPKR", RANDOM_POWER_CONSUMPTION, LocalDateTime.now());
    }

    @Test
    @Order(1) //testing the unique ID means that this test should run first
    void testGetIdShouldEndUpWithCorrectUniqueNumberDevice() {
        assertTrue(amazonAlexa.getId().endsWith("0"), "ID has to end with 0 if the device is the first initialized");

        AmazonAlexa amazonAlexa1 = new AmazonAlexa("SPKR1", RANDOM_POWER_CONSUMPTION, LocalDateTime.now());
        assertTrue(amazonAlexa1.getId().endsWith("1"), "ID has to end with 1 if the device is the second initialized");
    }

    @Test
    void testGetIdShouldStartWithShortTypeName() {
        assertTrue(amazonAlexa.getId().startsWith("SPKR"), "ID has to start with BLB if the device is thermostat");
    }

    @Test
    void testGetIdIdShouldHaveNameInTheMiddle() {
        String[] parts = amazonAlexa.getId().split("-");
        assertEquals(amazonAlexa.getName(), parts[1]);
    }

}