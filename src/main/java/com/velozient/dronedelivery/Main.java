package com.velozient.dronedelivery;

import com.velozient.dronedelivery.view.DeliveryManagerView;
import com.velozient.dronedelivery.view.console.DeliveryManagerViewConsole;

public class Main {
    public static void main(String[] args) {
        DeliveryManagerView deliveryManagerView = new DeliveryManagerViewConsole(args);
        deliveryManagerView.init();
    }

}