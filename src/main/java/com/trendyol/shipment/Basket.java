package com.trendyol.shipment;

import java.util.*;
import java.util.stream.Collectors;

public class Basket {

    private List<Product> products;

    public static final int PRODUCT_SHIPMENT_THRESHOLD = 3;

    public ShipmentSize getShipmentSize() {
        Map<ShipmentSize, Long> shipmentSizeFrequencyMap = products.stream()
                .collect(Collectors.groupingBy(Product::getSize, Collectors.counting()));

        return shipmentSizeFrequencyMap.entrySet()
                .stream()
                .filter(shipmentSizeFrequency -> shipmentSizeFrequency.getValue() >= PRODUCT_SHIPMENT_THRESHOLD)
                .max(Map.Entry.comparingByKey())
                .map(shipmentSizeFrequency ->
                        ShipmentSize.getUpperShipmentSize(shipmentSizeFrequency.getKey()))
                .orElse(getMaxValue());
    }

    private ShipmentSize getMaxValue() {
        return Collections.max(products, Comparator.comparingInt(product -> product.getSize().ordinal())).getSize();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
