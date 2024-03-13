package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import java.time.LocalDateTime;

public class RgbBulb extends IoTDeviceBase {
    static int uniqueNumberDevice = -1;
    private String bulbID;
    DeviceType type;

    public RgbBulb(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);

        type = DeviceType.BULB;
        uniqueNumberDevice++;
        bulbID = type.getShortName() + '-' + name + '-' + uniqueNumberDevice;
    }

    @Override
    public DeviceType getType() {
        return type;
    }

    @Override
    public String getId() {
        return bulbID;
    }

}
