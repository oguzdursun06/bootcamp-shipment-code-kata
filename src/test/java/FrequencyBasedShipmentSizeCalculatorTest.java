import com.trendyol.shipment.*;
import com.trendyol.shipment.exceptions.ShipmentBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
class FrequencyBasedShipmentSizeCalculatorTest {


    private ShipmentSizeCalculator shipmentSizeCalculator;

    @BeforeEach
    void setUp() {
        shipmentSizeCalculator = new FrequencyBasedShipmentSizeCalculator();
    }

    @ParameterizedTest
    @MethodSource("shipmentSizeOfProductsAndBasketShipmentSize")
    void shouldGetOrderShipmentSizeAsExpected(List<ShipmentSize> shipmentSizesOfProducts, ShipmentSize orderShipmentSize) {
        final var products = shipmentSizesOfProducts.stream().map(Product::create).collect(Collectors.toList());

        assertThat(shipmentSizeCalculator.calculateSize(products), equalTo(orderShipmentSize));
    }

    @Test
    void shouldThrowExceptionWhenProductListIsEmpty(){
        List<Product> products = List.of();

        Assertions.assertThrows(ShipmentBaseException.class, () -> shipmentSizeCalculator.calculateSize(products));
    }

    @Test
    void shouldThrowExceptionWhenProductListIsNull(){
        Assertions.assertThrows(ShipmentBaseException.class, () -> shipmentSizeCalculator.calculateSize(null));
    }

    private static Stream<Arguments> shipmentSizeOfProductsAndBasketShipmentSize() {
        return Stream.of(
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL), ShipmentSize.SMALL),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.SMALL), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.MEDIUM), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.LARGE), ShipmentSize.LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.SMALL), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.LARGE), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.SMALL), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.SMALL, ShipmentSize.MEDIUM, ShipmentSize.MEDIUM, ShipmentSize.LARGE), ShipmentSize.LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.MEDIUM), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.SMALL, ShipmentSize.MEDIUM, ShipmentSize.MEDIUM), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.MEDIUM, ShipmentSize.MEDIUM), ShipmentSize.MEDIUM),
                Arguments.of(Arrays.asList(ShipmentSize.MEDIUM, ShipmentSize.MEDIUM, ShipmentSize.MEDIUM), ShipmentSize.LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.MEDIUM, ShipmentSize.MEDIUM, ShipmentSize.MEDIUM, ShipmentSize.MEDIUM), ShipmentSize.LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.MEDIUM, ShipmentSize.LARGE), ShipmentSize.LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.MEDIUM, ShipmentSize.MEDIUM, ShipmentSize.LARGE), ShipmentSize.LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.MEDIUM, ShipmentSize.LARGE, ShipmentSize.LARGE), ShipmentSize.LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.X_LARGE, ShipmentSize.X_LARGE, ShipmentSize.X_LARGE), ShipmentSize.X_LARGE),
                Arguments.of(Arrays.asList(ShipmentSize.LARGE, ShipmentSize.LARGE, ShipmentSize.LARGE), ShipmentSize.X_LARGE)
        );
    }
}
