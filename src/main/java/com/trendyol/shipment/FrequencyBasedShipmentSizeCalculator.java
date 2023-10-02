package com.trendyol.shipment;

import com.trendyol.shipment.exceptions.ShipmentBaseException;

import java.util.*;
import java.util.stream.Collectors;

public class FrequencyBasedShipmentSizeCalculator implements ShipmentSizeCalculator{

    private static final int PRODUCT_SHIPMENT_THRESHOLD = 3;

    @Override
    public ShipmentSize calculateSize(List<Product> products) {
        if(products == null){
            throw new ShipmentBaseException("Product list is null, shipment size can not be calculated");
        }

        if(products.isEmpty()){
            throw new ShipmentBaseException("Product list is empty, shipment size can not be calculated");
        }

        Map<ShipmentSize, Long> shipmentSizeFrequencyMap = getShipmentSizeFrequencyMap(products);
        Optional<ShipmentSize> largestShipmentSizeGreaterThanThreshold = getLargestShipmentSizeGreaterThanThreshold(shipmentSizeFrequencyMap);

        return largestShipmentSizeGreaterThanThreshold.isPresent() ?
                ShipmentSize.getUpperShipmentSize(largestShipmentSizeGreaterThanThreshold.get()) :
                getLargestShipmentSize(products);
    }

    private Optional<ShipmentSize> getLargestShipmentSizeGreaterThanThreshold(Map<ShipmentSize, Long> shipmentSizeFrequencyMap) {
        return shipmentSizeFrequencyMap.entrySet()
                .stream()
                .filter(shipmentSizeFrequency -> shipmentSizeFrequency.getValue() >= PRODUCT_SHIPMENT_THRESHOLD)
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey);
    }

    private Map<ShipmentSize, Long> getShipmentSizeFrequencyMap(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getSize, Collectors.counting()));
    }

    private ShipmentSize getLargestShipmentSize(List<Product> products) {
        return Collections.max(products, Comparator.comparingInt(product -> product.getSize().ordinal())).getSize();
    }
}
