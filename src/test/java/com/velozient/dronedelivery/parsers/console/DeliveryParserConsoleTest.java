package com.velozient.dronedelivery.parsers.console;

import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;
import com.velozient.dronedelivery.parsers.DeliveryParser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class DeliveryParserConsoleTest {
    DeliveryParser deliveryParser = new DeliveryParserConsole();

    @Test
    public void parseDronesSuccess() {
        Drone[] expectedArray = {
                new Drone("Falcon", 200),
                new Drone("Eagle", 250),
                new Drone("Hawk", 175)};
        List<Drone> drones = deliveryParser.parseDrones("[Falcon], [200], [Eagle], [250], [Hawk], [175]");
        assertArrayEquals(expectedArray, drones.toArray());
    }

    @Test(expected = Exception.class)
    public void parseDroneFailsWithInvalidFormat() {
        deliveryParser.parseDrones("DADFS [200], [Eagle], [250], [Hawk], [175]");
    }

    @Test
    public void parseLocationsSuccess() {
        Location[] expectedLocations = {
                new Location("The Grove", 20),
                new Location("Central Park", 30),
                new Location("The Met", 10)
        };
        String[] inputLines = {
                "", // Drones line
                "[The Grove], [20]",
                "[Central Park], [30]",
                "[The Met], [10]"};
        List<Location> locations = deliveryParser.parseLocations(String.join(",", Arrays.copyOfRange(inputLines, 1, inputLines.length)));
        assertArrayEquals(expectedLocations, locations.toArray());
    }

    @Test(expected = Exception.class)
    public void parseLocationInvalidFormat() {
        deliveryParser.parseLocations("DADAS[The Grove], [20]DASDAD,DASDASD,DADA");
    }
}
