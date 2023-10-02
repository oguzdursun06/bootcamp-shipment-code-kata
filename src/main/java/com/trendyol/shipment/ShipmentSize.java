package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public static ShipmentSize getUpperShipmentSize(ShipmentSize shipmentSize) {
        if(shipmentSize.equals(X_LARGE)){
            return X_LARGE;
        }
        return ShipmentSize.values()[shipmentSize.ordinal() + 1];
    }
}
