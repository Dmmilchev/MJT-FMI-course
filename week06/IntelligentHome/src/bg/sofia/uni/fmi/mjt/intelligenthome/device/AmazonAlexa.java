package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import java.time.LocalDateTime;

public class AmazonAlexa extends IoTDeviceBase {
    static int uniqueNumberDevice = -1;
    private String alexaID;
    private DeviceType type;

    public AmazonAlexa(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);

        type = DeviceType.SMART_SPEAKER;
        uniqueNumberDevice++;
        alexaID = type.getShortName() + '-' + name + '-' + uniqueNumberDevice;
    }

    @Override
    public DeviceType getType() {
        return type;
    }

    @Override
    public String getId() {
        return alexaID;
    }

}
