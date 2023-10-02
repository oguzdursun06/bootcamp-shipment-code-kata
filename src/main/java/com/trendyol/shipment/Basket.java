package com.trendyol.shipment;

import java.util.*;
import java.util.stream.Collectors;

public class Basket {

    private List<Product> products;

    private final ShipmentSizeCalculator shipmentSizeCalculator = new FrequencyBasedShipmentSizeCalculator();

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
