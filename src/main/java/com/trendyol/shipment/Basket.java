package com.trendyol.shipment;

import java.util.*;

public class Basket {

    private List<Product> products;

    private final ShipmentSizeCalculator shipmentSizeCalculator;

    public Basket(ShipmentSizeCalculator shipmentSizeCalculator) {
        this.shipmentSizeCalculator = shipmentSizeCalculator;
    }

    public ShipmentSize getShipmentSize() {
        return shipmentSizeCalculator.calculateSize(products);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
