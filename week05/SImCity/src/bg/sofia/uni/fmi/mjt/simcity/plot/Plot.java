package bg.sofia.uni.fmi.mjt.simcity.plot;

import bg.sofia.uni.fmi.mjt.simcity.exception.BuildableAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.simcity.exception.BuildableNotFoundException;
import bg.sofia.uni.fmi.mjt.simcity.exception.InsufficientPlotAreaException;
import bg.sofia.uni.fmi.mjt.simcity.property.buildable.Buildable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Plot<E extends Buildable> implements PlotAPI<E>{
    private final int initialBuildableArea;
    private int remainingBuildableArea;
    private Map<String, E> plot;
    public Plot(int buildableArea) {
        this.initialBuildableArea = buildableArea;
        this.remainingBuildableArea = buildableArea;
        this.plot = new HashMap<>();
    }

    /**
     * Constructs a buildable on the plot.
     *
     * @param address   the address where the buildable should be constructed.
     * @param buildable the buildable that should be constructed on the given address.
     * @throws IllegalArgumentException        if the address is null or blank.
     * @throws IllegalArgumentException        if the buildable is null.
     * @throws BuildableAlreadyExistsException if the address is already occupied on the plot.
     * @throws InsufficientPlotAreaException   if the required area exceeds the remaining plot area.
     */
    @Override
    public void construct(String address, E buildable) throws BuildableAlreadyExistsException, InsufficientPlotAreaException {
        if (address == null) {
            throw new IllegalArgumentException("address can't be null");
        }
        if (address.isBlank()) {
            throw new IllegalArgumentException("address can't be blank");
        }
        if (buildable == null) {
            throw new IllegalArgumentException("buildable can't be null");
        }
        if (plot.containsKey(address)) {
            throw new BuildableAlreadyExistsException("address is already occupied on the plot");
        }
        if (getRemainingBuildableArea() < buildable.getArea()) {
            throw new InsufficientPlotAreaException("required area exceeds the remaining plot area");
        }

        plot.put(address, buildable);
        remainingBuildableArea -= buildable.getArea();
    }

    /**
     * Constructs multiple buildables on the plot.
     * This method ensures that either all operations are successfully completed
     * or no changes are made to the plot's state.
     *
     * @param buildables a Map containing the addresses and corresponding buildable entities.
     * @throws IllegalArgumentException        if the map of buildables is null, empty.
     * @throws BuildableAlreadyExistsException if any of the addresses is already occupied on the plot.
     * @throws InsufficientPlotAreaException   if the combined area of the provided buildables exceeds
     *                                         the remaining plot area.
     */
    @Override
    public void constructAll(Map<String, E> buildables) throws IllegalArgumentException, BuildableAlreadyExistsException, InsufficientPlotAreaException {
        if (buildables == null) {
            throw new IllegalArgumentException("buildable can't be null");
        }
        if (buildables.isEmpty()) {
            throw new IllegalArgumentException("buildable can't be empty");
        }
        for (String address : buildables.keySet()) {
            if (plot.containsKey(address)){
                throw new BuildableAlreadyExistsException("address is already occupied on the plot");
            }
        }
        int combinedArea = 0;
        for (E buildable : buildables.values()) {
            combinedArea += buildable.getArea();
        }
        if (combinedArea > getRemainingBuildableArea()) {
            throw new InsufficientPlotAreaException("combined area exceeds remaining area");
        }

        plot.putAll(buildables);
        remainingBuildableArea -= combinedArea;
    }

    /**
     * Demolishes a buildable from the plot.
     *
     * @param address the address of the buildable which should be demolished.
     * @throws IllegalArgumentException   if the provided address is null or blank.
     * @throws BuildableNotFoundException if buildable with such address does not exist on the plot.
     */
    @Override
    public void demolish(String address) throws IllegalArgumentException, BuildableNotFoundException {
        if (address == null) {
            throw new IllegalArgumentException("address can't be null");
        }
        if (address.isBlank()) {
            throw new IllegalArgumentException("address can't be blank");
        }
        if (!plot.containsKey(address)) {
            throw new BuildableNotFoundException("address can't be found on the plot");
        }

        remainingBuildableArea += plot.get(address).getArea();
        plot.remove(address);
    }

    /**
     * Demolishes all buildables from the plot.
     */
    @Override
    public void demolishAll() {
        plot.clear();
        remainingBuildableArea = initialBuildableArea;
    }

    /**
     * Retrieves all buildables present on the plot.
     *
     * @return An unmodifiable copy of the buildables present on the plot.
     */
    @Override
    public Map<String, E> getAllBuildables() {
        return plot;
    }

    /**
     * Retrieves the remaining buildable area on the plot.
     *
     * @return The remaining buildable area on the plot.
     */
    @Override
    public int getRemainingBuildableArea() {
        return remainingBuildableArea;
    }
    public int getInitialBuildableArea() {
        return initialBuildableArea;
    }
    public Map<String, E> getPlot() {
        return plot;
    }
}
