package bg.sofia.uni.fmi.mjt.simcity.property;

import bg.sofia.uni.fmi.mjt.simcity.property.billable.Billable;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.Buildable;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.BuildableType;
import bg.sofia.uni.fmi.mjt.simcity.utility.UtilityType;

import java.util.Map;

public class Building implements Billable{
    private final Map<UtilityType, Double> utilityConsumption;
    private final BuildableType type;
    private final int area;
    public Building(Map<UtilityType, Double> utilityConsumption, BuildableType type, int area) {
        this.utilityConsumption = utilityConsumption;
        this.type = type;
        this.area = area;
    }
     /**
     * Retrieves the monthly water consumption of the billable building.
     *
     * @return The monthly water consumption of the building in cubic meters (m³)
     */
    @Override
    public double getWaterConsumption() {
        return utilityConsumption.get(UtilityType.WATER);
    }

    /**
     * Retrieves the monthly electricity consumption of the billable building.
     *
     * @return The monthly electricity consumption of the building in kilowatt-hours (kWh)
     */
    @Override
    public double getElectricityConsumption() {
        return utilityConsumption.get(UtilityType.ELECTRICITY);
    }

    /**
     * Retrieves the monthly natural gas consumption of the billable building.
     *
     * @return The monthly natural gas consumption of the building in cubic meters (m³)
     */
    @Override
    public double getNaturalGasConsumption() {
        return utilityConsumption.get(UtilityType.NATURAL_GAS);
    }

    /**
     * Retrieves the type of the building.
     *
     * @return The specific type of the building, represented by a BuildableType.
     * Examples include Residential, Commercial, Industrial, etc.
     */
    @Override
    public BuildableType getType() {
        return type;
    }

    /**
     * Retrieves the total area of the building.
     *
     * @return The total area of the building in square metric units.
     */
    @Override
    public int getArea() {
        return area;
    }
}
