package com.velozient.dronedelivery;

import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;
import com.velozient.dronedelivery.models.Trip;

import java.util.*;

public class DeliveryPlanner {
    /*
     * We order the drones and locations for the ones with greater weight and capacity so that those can go first
     * which will avoid having filled the trip for the bigger drones with small locations leaving no option
     * but to create a new trip because the smaller ones don't have space for these locations
     */
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
                    } else if (drone.getMaxWeightCapacity() > location.getWeight()
                            && drone.getCurrentTrip().getLocations().size() > 1) {
                        drone.addTrip(new Trip());
                        bestCapacityDrone = drone;
                    }
                }
                if (bestCapacityDrone != null) {
                    bestCapacityDrone.getCurrentTrip().addLocation(location);
                    locationsQueue.remove();
                } else {
                    locationsQueue.remove();
                }
            }
        }
    }

}
