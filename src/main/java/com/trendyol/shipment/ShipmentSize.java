package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public static ShipmentSize getUpperShipmentSize(ShipmentSize shipmentSize) {
        if(ShipmentSize.values().length - 1 == shipmentSize.ordinal()){
            return ShipmentSize.values()[shipmentSize.ordinal()];
        }
        return ShipmentSize.values()[shipmentSize.ordinal() + 1];
    }
}
