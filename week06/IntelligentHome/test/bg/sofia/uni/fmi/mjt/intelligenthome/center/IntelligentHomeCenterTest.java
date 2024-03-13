package bg.sofia.uni.fmi.mjt.intelligenthome.center;

import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceAlreadyRegisteredException;
import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceNotFoundException;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.*;
import bg.sofia.uni.fmi.mjt.intelligenthome.storage.DeviceStorage;
import bg.sofia.uni.fmi.mjt.intelligenthome.storage.MapDeviceStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntelligentHomeCenterTest {

    @Test
    void testRegisterShouldThrowIllegalArgumentExceptionIfPassedNull() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        assertThrows(IllegalArgumentException.class,
                () -> intelligentHomeCenter.register(null),
                "IllegalArgumentException has to be thrown as we pass null in register");
    }

    @Test
    void testRegisterShouldThrowDeviceAlreadyRegisteredException() throws IllegalArgumentException, DeviceAlreadyRegisteredException {
        DeviceStorage deviceStorage = mock();
        when(deviceStorage.exists(any())).thenReturn(Boolean.TRUE);
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        IoTDevice device = mock();

        assertThrows(DeviceAlreadyRegisteredException.class,
                () -> intelligentHomeCenter.register(device),
                "DeviceAlreadyRegisteredException has to be thrown as we push same device 2 times");
    }

    @Test
    void testRegisterShouldAddDeviceToStorageIfRegistered() throws IllegalArgumentException, DeviceAlreadyRegisteredException {
        //not sure if it's possible to test this with mocking?
        DeviceStorage deviceStorage = new MapDeviceStorage();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        IoTDevice device = mock();
        when(device.getId()).thenReturn("ID");

        intelligentHomeCenter.register(device);
        assertTrue(deviceStorage.exists(device.getId()),
                "device with same ID has to be in storage as it is registered");
    }

    @Test
    void testUnregisterShouldThrowIllegalArgumentExceptionIfPassedNull() throws DeviceAlreadyRegisteredException {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.unregister(null));
    }

    @Test
    void testUnregisterShouldThrowDeviceNotFoundExceptionIfThereIsNotSuchDevice() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        IoTDevice device = mock();
        when(device.getId()).thenReturn("UniqueID");

        assertThrows(DeviceNotFoundException.class, () -> intelligentHomeCenter.unregister(device));
    }

    @Test
    void testUnregisterShouldNotBePresentAtStorageIfUnregistered() {
        DeviceStorage deviceStorage = mock();
        when(deviceStorage.exists(any())).thenReturn(Boolean.FALSE);
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        IoTDevice device = mock();

        assertThrows(DeviceNotFoundException.class, () -> intelligentHomeCenter.unregister(device),
                "Should throw deviceNotFoundException as deviceStorage.exists() return false");
    }

    @Test
    void testGetDeviceByIdShouldThrowIllegalArgumentExceptionIfPassedNull() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getDeviceById(null));
    }

    @Test
    void testGetDeviceByIdShouldThrowDeviceNotFoundExceptionIfDeviceIsNotFound() {
        DeviceStorage deviceStorage = mock();
        when(deviceStorage.exists(any())).thenReturn(Boolean.FALSE);
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        assertThrows(DeviceNotFoundException.class, () -> intelligentHomeCenter.getDeviceById("anything"));
    }

    @Test
    void testGetDeviceByIdShouldReturnTheDeviceWithTheSameID() throws DeviceNotFoundException {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        IoTDevice device = mock();
        when(device.getId()).thenReturn("ID");

        when(deviceStorage.exists(device.getId())).thenReturn(Boolean.TRUE);
        when(deviceStorage.get(device.getId())).thenReturn(device);

        assertEquals(device, intelligentHomeCenter.getDeviceById(device.getId()));
    }

    @Test
    void testGetDeviceQuantityPerTypeShouldThrowIllegalArgumentExceptionIfPassedNull() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getDeviceQuantityPerType(null));
    }

    @Test
    void testGetDeviceQuantityPerTypeShouldReturnZeroIfStorageIsEmpty() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        Collection<IoTDevice> emptyCollection = Collections.emptyList();
        when(deviceStorage.listAll()).thenReturn(emptyCollection);

        assertEquals(0, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.BULB),
                "Should return 0 as there aren't any bulbs in storage");
        assertEquals(0, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.SMART_SPEAKER),
                "Should return 0 as there aren't any smart speakers in storage");
        assertEquals(0, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.THERMOSTAT),
                "Should return 0 as there aren't any thermostats in storage");
    }

    @Test
    void testGetDeviceQuantityPerTypeTestingPositiveQuantities() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        //RgbBulb, WiFiThermostat, AmazonAlexa
        //BLB,    TMST,           SPKR
        //mocking 4 bulbs
        RgbBulb bulb1 = mock();
        when(bulb1.getType()).thenReturn(DeviceType.BULB);
        RgbBulb bulb2 = mock();
        when(bulb2.getType()).thenReturn(DeviceType.BULB);
        RgbBulb bulb3 = mock();
        when(bulb3.getType()).thenReturn(DeviceType.BULB);
        RgbBulb bulb4 = mock();
        when(bulb4.getType()).thenReturn(DeviceType.BULB);

        //mocking 3 AmazonAlexa's
        AmazonAlexa spkr1 = mock();
        when(spkr1.getType()).thenReturn(DeviceType.SMART_SPEAKER);
        AmazonAlexa spkr2 = mock();
        when(spkr2.getType()).thenReturn(DeviceType.SMART_SPEAKER);
        AmazonAlexa spkr3 = mock();
        when(spkr3.getType()).thenReturn(DeviceType.SMART_SPEAKER);

        //mocking 2 thermostats
        WiFiThermostat tmst1 = mock();
        when(tmst1.getType()).thenReturn(DeviceType.THERMOSTAT);
        WiFiThermostat tmst2 = mock();
        when(tmst2.getType()).thenReturn(DeviceType.THERMOSTAT);

        Collection<IoTDevice> collection = List.of(bulb1, bulb2, bulb3, bulb4, spkr1, spkr2, spkr3, tmst1, tmst2);
        when(deviceStorage.listAll()).thenReturn(collection);

        assertEquals(4, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.BULB),
                "Should return 4 as there are 4 bulbs in storage");
        assertEquals(3, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.SMART_SPEAKER),
                "Should return 3 as there are 3 smart speakers in storage");
        assertEquals(2, intelligentHomeCenter.getDeviceQuantityPerType(DeviceType.THERMOSTAT),
                "Should return 2 as there are 2 thermostats in storage");
    }

    @Test
    void testGetTopNDevicesByPowerConsumptionShouldReturnIllegalArgumentExceptionIfNegativeIsPassed() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getTopNDevicesByPowerConsumption(-5));
    }

    @Test
    void testGetTopNDevicesByPowerConsumptionShouldReturnTopTwoIfPassedTwo() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        IoTDevice device1 = mock();
        when(device1.getPowerConsumption()).thenReturn(5.0);
        when(device1.getId()).thenReturn("1");
        IoTDevice device2 = mock();
        when(device2.getPowerConsumption()).thenReturn(3.0);
        when(device2.getId()).thenReturn("2");
        IoTDevice device3 = mock();
        when(device3.getPowerConsumption()).thenReturn(1.0);
        when(device3.getId()).thenReturn("3");

        when(deviceStorage.listAll()).thenReturn(List.of(device1, device2, device3));

        ArrayList<String> topDevices = new ArrayList<>(intelligentHomeCenter.getTopNDevicesByPowerConsumption(2));
        assertEquals("1", topDevices.getFirst(), "first element has to be 1 as it has the most consumption");
        assertEquals("2", topDevices.get(1), "second element has to be 2 as it has the second most consumption");
    }

    @Test
    void testGetFirstNDevicesByRegistrationShouldReturnIllegalArgumentExceptionIfNegativeIsPassed() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        assertThrows(IllegalArgumentException.class, () -> intelligentHomeCenter.getFirstNDevicesByRegistration(-5));
    }

    @Test
    void testGetFirstNDevicesByRegistrationShouldReturnFirstTwoIfTwoIsPassed() {
        DeviceStorage deviceStorage = mock();
        IntelligentHomeCenter intelligentHomeCenter = new IntelligentHomeCenter(deviceStorage);

        IoTDevice device1 = mock();
        when(device1.getRegistration()).thenReturn(5L);
        when(device1.getId()).thenReturn("1");
        IoTDevice device2 = mock();
        when(device2.getRegistration()).thenReturn(3L);
        when(device2.getId()).thenReturn("2");
        IoTDevice device3 = mock();
        when(device3.getRegistration()).thenReturn(1L);
        when(device3.getId()).thenReturn("3");

        when(deviceStorage.listAll()).thenReturn(List.of(device1, device2, device3));

        ArrayList<IoTDevice> topDevices = new ArrayList<>(intelligentHomeCenter.getFirstNDevicesByRegistration(2));
        assertEquals("1", topDevices.getFirst().getId(), "first element has to be 1 as it has the most consumption");
        assertEquals("2", topDevices.get(1).getId(), "second element has to be 2 as it has the second most consumption");
    }
}
