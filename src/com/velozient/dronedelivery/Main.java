package com.velozient.dronedelivery;

import com.velozient.dronedelivery.controller.DeliveryPlanner;
import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;
import com.velozient.dronedelivery.parsers.DeliveryParser;
import com.velozient.dronedelivery.readers.file.DeliveryReaderFile;
import com.velozient.dronedelivery.view.DeliveryManagerView;
import com.velozient.dronedelivery.view.console.DeliveryManagerViewConsole;
import com.velozient.dronedelivery.parsers.console.DeliveryParserConsole;
import com.velozient.dronedelivery.readers.DeliveryReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        DeliveryManagerView deliveryManagerView = new DeliveryManagerViewConsole(args);
        deliveryManagerView.init();
    }

}