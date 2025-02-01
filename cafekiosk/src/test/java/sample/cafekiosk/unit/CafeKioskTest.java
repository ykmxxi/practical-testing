package sample.cafekiosk.unit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

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

}
