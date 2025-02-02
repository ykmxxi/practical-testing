package sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;
import sample.cafekiosk.unit.order.Order;

class CafeKioskTest {

    @Test
    void bad_add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        System.out.println("추가된 음료 수 : " + cafeKiosk.getOrderBeverages().size());
        System.out.println("추가된 음료 이름 : " + cafeKiosk.getOrderBeverages().getFirst().getName());
    }

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        assertThat(cafeKiosk.getOrderBeverages()).hasSize(1);
        assertThat(cafeKiosk.getOrderBeverages().getFirst().getName()).isEqualTo("아메리카노");
    }

    @Test
    void addSeveralBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getOrderBeverages()).hasSize(2);
        assertThat(cafeKiosk.getOrderBeverages().getFirst()).isEqualTo(americano);
        assertThat(cafeKiosk.getOrderBeverages().getLast()).isEqualTo(americano);
    }

    @Test
    void addZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상 주문 가능합니다.");
    }

    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getOrderBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getOrderBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        assertThat(cafeKiosk.getOrderBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getOrderBeverages()).isEmpty();
    }

    @Test
    void createOrder() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder(LocalDateTime.of(2025, 2, 2, 10, 0));

        assertThat(order.getOrderBeverages()).hasSize(1)
                .contains(americano);
    }

    @Test
    void createOrderOutsideOpenTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2025, 2, 2, 9, 59)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에 문의하세요.");
    }

}
