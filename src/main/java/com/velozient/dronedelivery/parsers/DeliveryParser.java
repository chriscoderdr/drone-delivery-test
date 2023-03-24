package com.velozient.dronedelivery.parsers;

import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;

import java.io.FileNotFoundException;
import java.util.List;

public interface DeliveryParser {
    List<Drone> parseDrones(String input);
    List<Location> parseLocations(String input);
}
