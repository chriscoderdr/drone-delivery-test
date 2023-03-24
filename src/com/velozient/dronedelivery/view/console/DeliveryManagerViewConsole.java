package com.velozient.dronedelivery.view.console;

import com.velozient.dronedelivery.controller.DeliveryManagerViewController;
import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;
import com.velozient.dronedelivery.models.Trip;
import com.velozient.dronedelivery.view.DeliveryManagerView;

import java.util.List;

public class DeliveryManagerViewConsole implements DeliveryManagerView {
    List<Drone> drones;
    String[] commandArgs;
    DeliveryManagerViewController viewController;

    public DeliveryManagerViewConsole(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    private void selectFile(boolean fileArgPassed) {
        String filePath = "delivery_info.txt";
        if (fileArgPassed) {
            if (commandArgs.length < 2) {
                showFileArgNotFound();
                return;
            }
            filePath = commandArgs[1];
        }
        this.viewController = new DeliveryManagerViewController(this, filePath);
        this.viewController.loadData();
    }

    private void showFileArgNotFound() {
        System.out.println("You passed -file argument but didn't pass a file please do so.");
    }

    public void init() {
        if (commandArgs.length > 0) {
            switch (commandArgs[0]) {
                case CommandLineArgs.help -> showHelp();
                case CommandLineArgs.path -> selectFile(true);
                default -> showInvalidArgs();
            }
            return;
        }
        selectFile(false);
    }


    private void showHelp() {
        System.out.println("DroneDeliveryService\nOptions:\n-path: File path following the pattern in delivery_info.txt sample file");
    }

    private void showInvalidArgs() {
        System.out.println("Invalid argument. Use -help to display argument list.");
    }

    @Override
    public void show() {
        for (Drone drone : drones) {
            List<Trip> trips = drone.getTrips();
            for (int i = 0; i < trips.size(); i++) {
                if (trips.get(i).getLocations().isEmpty()) {
                    continue;
                }
                if (i == 0) {
                    System.out.println(drone);
                }
                System.out.print("Trip #" + (i + 1));
                List<Location> locations = trips.get(i).getLocations();
                for (int j = 0; j < locations.size(); j++) {
                    Location location = locations.get(j);
                    if (j == 0) {
                        System.out.print("\n");
                    }
                    System.out.print(location);
                    if (j == locations.size() - 1) {
                        System.out.print("\n");
                    }
                }
                if (i == trips.size() - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    @Override
    public void showParseFileError() {
        System.out.println("Error parsing file.");
    }

    @Override
    public void onDataLoaded(List<Drone> drones) {
        this.drones = drones;
        show();
    }
}
