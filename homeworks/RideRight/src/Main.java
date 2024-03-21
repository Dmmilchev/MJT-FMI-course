import bg.sofia.uni.fmi.mjt.itinerary.City;
import bg.sofia.uni.fmi.mjt.itinerary.Journey;
import bg.sofia.uni.fmi.mjt.itinerary.Location;
import bg.sofia.uni.fmi.mjt.itinerary.RideRight;
import bg.sofia.uni.fmi.mjt.itinerary.search.AStar;
import bg.sofia.uni.fmi.mjt.itinerary.vehicle.VehicleType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        City sofia = new City("Sofia", new Location(new BigInteger("0"), new BigInteger("2000")));
        City burgas = new City("Burgas", new Location(new BigInteger("9000"), new BigInteger("1000")));
        City varna = new City("Varna", new Location(new BigInteger("9000"), new BigInteger("3000")));
        City tarnovo = new City("Tarnovo", new Location(new BigInteger("5000"), new BigInteger("3000")));
        City ruse = new City("Ruse", new Location(new BigInteger("7000"), new BigInteger("4000")));
        City plovdiv = new City("Plovdiv", new Location(new BigInteger("4000"), new BigInteger("1000")));
        City kardzhali = new City("Kardzhali", new Location(new BigInteger("3000"), new BigInteger("0")));
        City blagoevgrad = new City("Blagoevgrad", new Location(new BigInteger("0"), new BigInteger("1000")));

        Journey sofiaToBurgasPlane = new Journey(VehicleType.PLANE, sofia, burgas, new BigDecimal("150"));
        Journey sofiaToVarnaPlane = new Journey(VehicleType.PLANE, sofia, varna, new BigDecimal("300"));
        Journey varnaToSofiaPlane = new Journey(VehicleType.PLANE, varna, sofia, new BigDecimal("290"));
        Journey varnaToBurgasPlane = new Journey(VehicleType.PLANE, varna, burgas, new BigDecimal("200"));
        Journey burgasToVarnaPlane = new Journey(VehicleType.PLANE, burgas, varna, new BigDecimal("200"));
        Journey varnaToBurgasBus = new Journey(VehicleType.BUS, varna, burgas, new BigDecimal("60"));
        Journey burgasToVarnaBus = new Journey(VehicleType.BUS, burgas, varna, new BigDecimal("60"));
        Journey sofiaToTarnovoBus = new Journey(VehicleType.BUS, sofia, tarnovo, new BigDecimal("150"));
        Journey tarnovoToRuseBus = new Journey(VehicleType.BUS, tarnovo, ruse,  new BigDecimal("70"));
        Journey sofiaToBlagoevgradBus = new Journey(VehicleType.BUS, sofia, blagoevgrad, new BigDecimal("20"));
        Journey blagoevgradToSofiaBus = new Journey(VehicleType.BUS, blagoevgrad, sofia, new BigDecimal("20"));
        Journey sofiaToPlovdivBus = new Journey(VehicleType.BUS, sofia, plovdiv, new BigDecimal("90"));
        Journey plovdivToSofiaBus = new Journey(VehicleType.BUS, plovdiv, sofia, new BigDecimal("90"));
        Journey plovdivToTarnovoBus = new Journey(VehicleType.BUS, plovdiv, tarnovo, new BigDecimal("40"));
        Journey tarnovoToPlovdivBus = new Journey(VehicleType.BUS, tarnovo, plovdiv, new BigDecimal("40"));
        Journey plovdivToKardzhaliBus = new Journey(VehicleType.BUS, plovdiv, kardzhali, new BigDecimal("50"));
        Journey kardzhaliToPlovdivBus = new Journey(VehicleType.BUS, kardzhali, plovdiv, new BigDecimal("50"));
        Journey plovdivToBurgasBus = new Journey(VehicleType.BUS, plovdiv, burgas, new BigDecimal("90"));
        Journey burgasToPlovdivBus = new Journey(VehicleType.BUS, burgas, plovdiv, new BigDecimal("90"));


        RideRight rideRight = new RideRight();

        rideRight.addCity(sofia);
        rideRight.addCity(varna);
        rideRight.addCity(burgas);
        rideRight.addCity(tarnovo);

        rideRight.addJourney(sofiaToBurgasPlane);
        rideRight.addJourney(sofiaToVarnaPlane);
        rideRight.addJourney(varnaToSofiaPlane);
        rideRight.addJourney(varnaToBurgasPlane);
        rideRight.addJourney(burgasToVarnaPlane);
        rideRight.addJourney(varnaToBurgasBus);
        rideRight.addJourney(burgasToVarnaBus);
        rideRight.addJourney(sofiaToTarnovoBus);
        rideRight.addJourney(tarnovoToRuseBus);
        rideRight.addJourney(sofiaToBlagoevgradBus);
        rideRight.addJourney(blagoevgradToSofiaBus);
        rideRight.addJourney(sofiaToPlovdivBus);
        rideRight.addJourney(plovdivToSofiaBus);
        rideRight.addJourney(plovdivToTarnovoBus);
        rideRight.addJourney(tarnovoToPlovdivBus);
        rideRight.addJourney(plovdivToKardzhaliBus);
        rideRight.addJourney(kardzhaliToPlovdivBus);
        rideRight.addJourney(plovdivToBurgasBus);
        rideRight.addJourney(burgasToPlovdivBus);


        AStar aStar = new AStar(rideRight.getRoadNetwork());
        List<Journey> pathSV= aStar.findShortestPath(varna, kardzhali);
        System.out.println(pathSV);

        System.out.println(aStar.findShortestPath(sofia, burgas));
    }
}