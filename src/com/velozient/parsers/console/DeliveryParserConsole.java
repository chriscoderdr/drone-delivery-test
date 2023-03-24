package com.velozient.parsers.console;

import com.velozient.models.Drone;
import com.velozient.models.Location;
import com.velozient.parsers.DeliveryParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryParserConsole implements DeliveryParser {

    @Override
    public List<Drone> parseDrones(String dronesInput) {
        String[] droneStrings = dronesInput.split(",");
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < droneStrings.length; i += 2) {
            String name = droneStrings[i].trim().replaceAll("[\\[\\]]", "");
            int maxWeight = Integer.parseInt(droneStrings[i + 1].trim().replaceAll("[^0-9]", ""));
            drones.add(new Drone(name, maxWeight));
        }
        return drones;
    }

    @Override
    public List<Location> parseLocations(String locationsInput) {
        String[] locationStrings = locationsInput.split(",");
        List<Location> locations = new ArrayList<>();
        for (int i = 0; i < locationStrings.length; i += 2) {
            String name = locationStrings[i].trim().replaceAll("[\\[\\]]", "");
            int packageWeight = Integer.parseInt(locationStrings[i + 1].trim().replaceAll("[^0-9]", ""));
            locations.add(new Location(name, packageWeight));
        }
        return locations;
    }
}
