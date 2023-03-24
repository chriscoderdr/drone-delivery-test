package com.velozient.dronedelivery.view.console;

import com.velozient.dronedelivery.controller.DeliveryManagerViewController;
import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;
import com.velozient.dronedelivery.models.Trip;
import com.velozient.dronedelivery.view.DeliveryManagerView;

import java.nio.file.NoSuchFileException;
import java.util.List;

public class DeliveryManagerViewConsole implements DeliveryManagerView {
    List<Drone> drones;
    final String[] commandArgs;
    DeliveryManagerViewController viewController;

    public DeliveryManagerViewConsole(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    private void selectFile(boolean fileArgPassed) {
        String filePath = "samples/1.txt";
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
        System.out.println("""
                DroneDeliveryService
                Options:
                    -path: File path following the pattern (There are sample files in samples folder):
                """);
        showFileFormat();
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
                    } else {
                        System.out.print(", ");
                    }
                }
                if (i == trips.size() - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    @Override
    public void showParseFileError(Exception e) {
        if (e instanceof java.nio.file.NoSuchFileException) {
            System.out.println("File not found, make sure you input the correct path: " + ((NoSuchFileException) e).getFile());
        } else {
            System.out.println("Error parsing file. sample files can be found at samples folder in the project directory.\nPlease Make sure you follow the correct format:\n\n");
            showFileFormat();
        }
    }

    private void showFileFormat() {
        System.out.println("[Drone #1 Name], [#1 Maximum Weight], [Drone #2 Name], [#2 Maximum Weight], etc.\n" +
                "[Location #1 Name], [Location #1 Package Weight]");
    }

    @Override
    public void onDataLoaded(List<Drone> drones) {
        this.drones = drones;
        show();
    }

    @Override
    public void showErrorDroneLimitExceeded(int number) {
        System.out.println("A maximum of 100 drones can be set in the squad and you have: " + number + " drones");
    }
}
