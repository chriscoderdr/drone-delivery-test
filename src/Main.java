import com.velozient.controller.DeliveryPlanner;
import com.velozient.models.Drone;
import com.velozient.models.Location;
import com.velozient.parsers.DeliveryParser;
import com.velozient.parsers.console.DeliveryParserConsole;
import com.velozient.readers.DeliveryReader;
import com.velozient.readers.file.DeliveryReaderFile;
import com.velozient.view.DeliveryManagerView;
import com.velozient.view.console.DeliveryManagerViewConsole;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        DeliveryReader deliveryReader = new DeliveryReaderFile();

        String[] inputLines = deliveryReader.read().split(System.lineSeparator());

        DeliveryParser deliveryParser = new DeliveryParserConsole();
        List<Drone> drones = deliveryParser.parseDrones(inputLines[0]);
        List<Location> locations = deliveryParser.parseLocations(
                String.join(",", Arrays.copyOfRange(inputLines, 1, inputLines.length)));
        DeliveryPlanner deliveryPlanner = new DeliveryPlanner();
        deliveryPlanner.assignDeliveries(drones, locations);
        System.out.println(drones);
        System.out.println(locations);

        DeliveryManagerView deliveryManagerView = new DeliveryManagerViewConsole(drones);
        deliveryManagerView.showDeliveries();
    }

}