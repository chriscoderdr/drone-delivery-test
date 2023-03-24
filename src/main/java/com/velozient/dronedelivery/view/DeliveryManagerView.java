package com.velozient.dronedelivery.view;

import com.velozient.dronedelivery.models.Drone;

import java.util.List;

public interface DeliveryManagerView extends View {
    void init();
    void show();

    void showParseFileError(Exception e);

    void onDataLoaded(List<Drone> drones);

    void showErrorDroneLimitExceeded(int number);
}
