package bg.sofia.uni.fmi.mjt.intelligenthome.center.comparator;

import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class KWhComparatorTest {

    KWhComparator comparator;

    @BeforeEach
    void initializeComparator() {
        comparator = new KWhComparator();
    }
    @Test
    void testCompareSameObjectsShouldBeEqual() {
        IoTDevice device = mock();

        assertEquals(0, comparator.compare(device, device));
    }

    @Test
    void testCompareOlderDeviceShouldBeGreater() {
        IoTDevice device1 = mock();
        IoTDevice device2 = mock();

        when(device1.getPowerConsumptionKWh()).thenReturn(10L);
        when(device2.getPowerConsumptionKWh()).thenReturn(1000L);

        assertTrue(comparator.compare(device1, device2) > 0);
    }

}
