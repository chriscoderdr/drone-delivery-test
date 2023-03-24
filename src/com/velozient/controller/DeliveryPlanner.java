package com.velozient.controller;

import com.velozient.models.Drone;
import com.velozient.models.Location;

import java.util.List;

public class DeliveryPlanner {
    public void assignDeliveries(List<Drone> drones, List<Location> locations) {
        for (Location location : locations) {
            Drone bestDrone = null;
            for (Drone drone : drones) {
                if (bestDrone == null || drone.getRemainingCapacity() > bestDrone.getRemainingCapacity()) {
                    bestDrone = drone;
                }
            }
            if (bestDrone != null) {
                bestDrone.addTrip(location);
            }
        }
    }
}
