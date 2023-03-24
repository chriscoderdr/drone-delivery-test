package com.velozient.view.console;

import com.velozient.models.Drone;
import com.velozient.models.Location;
import com.velozient.view.DeliveryManagerView;

import java.util.ArrayList;
import java.util.List;

public class DeliveryManagerViewConsole implements DeliveryManagerView {
    List<Drone> drones;

    public DeliveryManagerViewConsole(List<Drone> drones) {
        this.drones = drones;
    }

    @Override
    public void showDeliveries() {
        for (Drone drone : drones) {
            System.out.println("[" + drone.getName() + "]");
            List<Location> locations = drone.getTrips();
            List<String> locationNames = new ArrayList<>();
            for (Location location : locations) {
                locationNames.add("[" + location.getName() + "]");
            }
            System.out.println("Trip #1");
            System.out.println(String.join(", ", locationNames));
        }
    }
}
