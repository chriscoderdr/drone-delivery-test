package com.velozient.view.console;

import com.velozient.models.Drone;
import com.velozient.models.Location;
import com.velozient.models.Trip;
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

            List<Trip> trips = drone.getTrips();
            for (int i = 0; i < trips.size(); i++) {
                if (trips.get(i).getLocations().isEmpty()) {
                    continue;
                }
                if (i == 0) {
                    System.out.println(drone + "" + drone.getMaxWeightCapacity());
                }
                System.out.print("Trip #" + (i + 1));
                List<Location> locations = trips.get(i).getLocations();
                for (int j = 0; j < locations.size(); j++) {
                    Location location = locations.get(j);
                    if (j == 0) {
                        System.out.print("\n");
                    }
                    System.out.print(location + " " + location.getWeight());
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
}
