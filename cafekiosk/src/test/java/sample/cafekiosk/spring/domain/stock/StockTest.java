package sample.cafekiosk.spring.domain.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StockTest {

    @DisplayName("재고 수량이 충분한지 체크한다.")
    @CsvSource(value = {"1,true", "2,false"})
    @ParameterizedTest
    void hasEnoughQuantity(int quantity, boolean expected) {
        Stock stock = Stock.create("001", 1);

        assertThat(stock.hasEnoughQuantity(quantity)).isEqualTo(expected);
    }

    @DisplayName("주어진 수량만큼 재고 수량을 차감한다.")
    @Test
    void deductQuantity() {
        Stock stock = Stock.create("001", 1);
        int quantity = 1;

        stock.deductQuantity(quantity);

        assertThat(stock.getQuantity()).isZero();
    }

    @DisplayName("재고 수량보다 많은 수량을 차감할 수 없다.")
    @Test
    void deductQuantity2() {
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        assertThatThrownBy(() -> stock.deductQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
