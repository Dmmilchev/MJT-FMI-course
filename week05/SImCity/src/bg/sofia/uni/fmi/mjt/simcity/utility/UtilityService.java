package bg.sofia.uni.fmi.mjt.simcity.utility;

import bg.sofia.uni.fmi.mjt.simcity.property.billable.Billable;

import java.util.HashMap;
import java.util.Map;

public class UtilityService implements UtilityServiceAPI{
    private final Map<UtilityType, Double> taxRates;

    public UtilityService(Map<UtilityType, Double> taxRates) {
        this.taxRates = taxRates;
    }
    /**
     * Retrieves the costs of a specific utility for a given billable building.
     *
     * @param utilityType The utility type used for the costs' calculation.
     * @param billable    The billable building for which the utility costs will be calculated.
     * @return The cost of the specified utility for the billable building.
     * @throws IllegalArgumentException if the utility or billable is null.
     */
    @Override
    public <T extends Billable> double getUtilityCosts(UtilityType utilityType, T billable) {
        if (utilityType == null) {
            throw new IllegalArgumentException("utilityType can't be null");
        }
        if (billable == null) {
            throw new IllegalArgumentException("billable can't be null");
        }

        return switch(utilityType){
            case WATER -> billable.getWaterConsumption() * taxRates.get(UtilityType.WATER);
            case ELECTRICITY -> billable.getElectricityConsumption() * taxRates.get(UtilityType.ELECTRICITY);
            case NATURAL_GAS -> billable.getNaturalGasConsumption() * taxRates.get(UtilityType.NATURAL_GAS);
        };
    }

    /**
     * Calculates the total utility costs for a given billable building.
     *
     * @param billable The billable building for which total utility costs are calculated.
     * @return The total cost of all utilities for the billable building.
     * @throws IllegalArgumentException if the billable is null.
     */
    @Override
    public <T extends Billable> double getTotalUtilityCosts(T billable) {
        if (billable == null) {
            throw new IllegalArgumentException("billable can't be null");
        }

        return  this.getUtilityCosts(UtilityType.WATER, billable) +
                this.getUtilityCosts(UtilityType.ELECTRICITY, billable) +
                this.getUtilityCosts(UtilityType.NATURAL_GAS, billable);
    }

    /**
     * Computes the absolute difference in utility costs between two billable buildings for each utility type.
     *
     * @param firstBillable  The first billable building used for the cost comparison.
     * @param secondBillable The second billable building used for the cost comparison.
     * @return An unmodifiable map containing the absolute difference in costs between the buildings for each
     * utility.
     * @throws IllegalArgumentException if any billable is null.
     */
    @Override
    public <T extends Billable> Map<UtilityType, Double> computeCostsDifference(T firstBillable, T secondBillable) {
        Map<UtilityType, Double> costDifference = new HashMap<>();

        costDifference.put(UtilityType.WATER,
                        Math.abs(
                        this.getUtilityCosts(UtilityType.WATER, firstBillable) -
                        this.getUtilityCosts(UtilityType.WATER, secondBillable))
        );
        costDifference.put(UtilityType.ELECTRICITY,
                        Math.abs(
                        this.getUtilityCosts(UtilityType.ELECTRICITY, firstBillable) -
                        this.getUtilityCosts(UtilityType.ELECTRICITY, secondBillable))
        );
        costDifference.put(UtilityType.NATURAL_GAS,
                        Math.abs(
                        this.getUtilityCosts(UtilityType.NATURAL_GAS, firstBillable) -
                        this.getUtilityCosts(UtilityType.NATURAL_GAS, secondBillable))
        );

        return costDifference;
    }
}
