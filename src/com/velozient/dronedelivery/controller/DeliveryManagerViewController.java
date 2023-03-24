package com.velozient.dronedelivery.controller;

import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;
import com.velozient.dronedelivery.parsers.DeliveryParser;
import com.velozient.dronedelivery.parsers.console.DeliveryParserConsole;
import com.velozient.dronedelivery.readers.DeliveryReader;
import com.velozient.dronedelivery.readers.file.DeliveryReaderFile;
import com.velozient.dronedelivery.view.DeliveryManagerView;
import com.velozient.dronedelivery.view.console.DeliveryManagerViewConsole;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DeliveryManagerViewController {
    List<Drone> drones;
    DeliveryManagerView view;
    String filePath;

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
            this.drones = deliveryParser.parseDrones(inputLines[0]);
            List<Location> locations = deliveryParser.parseLocations(
                    String.join(",", Arrays.copyOfRange(inputLines, 1, inputLines.length)));
            DeliveryPlanner deliveryPlanner = new DeliveryPlanner();
            deliveryPlanner.assignDeliveries(drones, locations);
            view.onDataLoaded(drones);
        } catch (IOException e) {
            view.showParseFileError();
        }
    }
}
