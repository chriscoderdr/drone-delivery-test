package com.velozient.dronedelivery.view;

import com.velozient.dronedelivery.models.Drone;
import com.velozient.dronedelivery.models.Location;

import java.util.List;

public interface DeliveryManagerView extends View {
    void init();
    void show();

    void showParseFileError();

    void onDataLoaded(List<Drone> drones);
}
