package com.trendyol.shipment;

import java.util.List;

public interface ShipmentSizeCalculator {

    ShipmentSize calculateSize(List<Product> products);
}
