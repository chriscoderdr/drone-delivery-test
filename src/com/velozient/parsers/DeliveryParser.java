package com.velozient.parsers;

import com.velozient.models.Drone;
import com.velozient.models.Location;

import java.io.FileNotFoundException;
import java.util.List;

public interface DeliveryParser {
    List<Drone> parseDrones(String input) throws FileNotFoundException;
    List<Location> parseLocations(String input) throws FileNotFoundException;
}
