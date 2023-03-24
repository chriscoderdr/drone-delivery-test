package com.velozient.controller;

import com.velozient.models.Drone;
import com.velozient.models.Location;
import com.velozient.models.Trip;

import java.util.*;

public class DeliveryPlanner {
    public void assignDeliveries(List<Drone> drones, List<Location> locations) {
        locations.sort((l1, l2) -> l2.getWeight() - l1.getWeight());
        drones.sort((d1, d2) -> d2.getMaxWeightCapacity() - d1.getMaxWeightCapacity());
        ArrayDeque<Location> locationsQueue = new ArrayDeque<>(locations);

        while (!locationsQueue.isEmpty()) {
            for (Location location : locationsQueue) {
                Drone bestCapacityDrone = null;
                for (Drone drone : drones) {
                    if (drone.getCurrentCapacity() > 0 && drone.getCurrentCapacity() >= location.getWeight() &&
                            (bestCapacityDrone == null || drone.getCurrentCapacity() > bestCapacityDrone.getCurrentCapacity())) {
                        bestCapacityDrone = drone;
                    } else if (drone.getMaxWeightCapacity() > location.getWeight() && drone.getCurrentTrip().getLocations().size() > 1) {
                        drone.addTrip(new Trip());
                        bestCapacityDrone = drone;
                    }
                }
                if (bestCapacityDrone != null) {
                    bestCapacityDrone.getCurrentTrip().addLocation(location);
                    locationsQueue.remove();
                }
            }
        }
    }

}
