package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import java.time.LocalDateTime;

public class WiFiThermostat extends IoTDeviceBase {
    static int uniqueNumberDevice = -1;
    private String thermostatID;
    private DeviceType type;

    public WiFiThermostat(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);

        type = DeviceType.THERMOSTAT;
        uniqueNumberDevice++;
        thermostatID = type.getShortName() + '-' + name + '-' + uniqueNumberDevice;
    }

    @Override
    public DeviceType getType() {
        return type;
    }

    @Override
    public String getId() {
        return thermostatID;
    }

}
