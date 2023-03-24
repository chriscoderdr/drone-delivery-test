package com.velozient.dronedelivery.controller;

import com.velozient.dronedelivery.DeliveryPlanner;
import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;
import com.velozient.dronedelivery.parsers.DeliveryParser;
import com.velozient.dronedelivery.parsers.console.DeliveryParserConsole;
import com.velozient.dronedelivery.readers.DeliveryReader;
import com.velozient.dronedelivery.readers.file.DeliveryReaderFile;
import com.velozient.dronedelivery.view.DeliveryManagerView;

import java.util.Arrays;
import java.util.List;

public class DeliveryManagerViewController {
    final DeliveryManagerView view;
    final String filePath;

    public DeliveryManagerViewController(DeliveryManagerView view, String filePath) {
        this.view = view;
        this.filePath = filePath;
    }

    public void loadData() {
        try {
            DeliveryReader deliveryReader = new DeliveryReaderFile(filePath);
            String[] inputLines;
            inputLines = deliveryReader.read().split(System.lineSeparator());
            DeliveryParser deliveryParser = new DeliveryParserConsole();
            List<Drone> drones = deliveryParser.parseDrones(inputLines[0]);
            if (drones.size() <= 100) {
                List<Location> locations = deliveryParser.parseLocations(
                        String.join(",", Arrays.copyOfRange(inputLines, 1, inputLines.length)));
                DeliveryPlanner deliveryPlanner = new DeliveryPlanner();
                deliveryPlanner.assignDeliveries(drones, locations);
                view.onDataLoaded(drones);
            } else {
                view.showErrorDroneLimitExceeded(drones.size());
            }
        } catch (Exception e) {
            view.showParseFileError(e);
        }
    }
}
