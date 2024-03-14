package bg.sofia.uni.fmi.mjt.flightscanner.airport;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;



public class AirportTest {

    @Test
    void testConstructorShouldThrowExceptionIfPassedNull () {
        assertThrows(IllegalArgumentException.class, () -> new Airport(null),
                "Constructor should throw IllegalArgumentException if null is passed");
    }

    @Test
    void testConstructorShouldThrowExceptionIfPassedEmptyString () {
        assertThrows(IllegalArgumentException.class, () -> new Airport(""),
                "Constructor should throw IllegalArgumentException if empty string is passed");
    }

    @Test
    void testConstructorShouldThrowExceptionIfPassedBlankString () {
        assertThrows(IllegalArgumentException.class, () -> new Airport("   "),
                "Constructor should throw IllegalArgumentException if blank string is passed");
    }

}
