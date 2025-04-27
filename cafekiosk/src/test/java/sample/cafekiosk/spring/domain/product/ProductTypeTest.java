package sample.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductTypeTest {

    @DisplayName("상품 타입이 재고 관련 타입인지 체크한다.")
    @CsvSource(value = {
            "BAKERY,true", "BOTTLE,true",
            "HANDMADE,false",
    })
    @ParameterizedTest
    void containsStockType(ProductType givenType, boolean expected) {
        assertThat(ProductType.containsStockType(givenType)).isEqualTo(expected);
    }
}
