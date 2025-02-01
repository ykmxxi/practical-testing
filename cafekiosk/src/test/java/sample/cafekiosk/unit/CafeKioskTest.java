package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;

import sample.cafekiosk.unit.beverage.Americano;

class CafeKioskTest {

    @Test
    void bad_add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);

        System.out.println("추가된 음료 수 : " + cafeKiosk.getOrderBeverages().size());
        System.out.println("추가된 음료 이름 : " + cafeKiosk.getOrderBeverages().getFirst().getName());
    }

}
