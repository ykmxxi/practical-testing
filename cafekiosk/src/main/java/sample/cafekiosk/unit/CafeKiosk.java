package sample.cafekiosk.unit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

public class CafeKiosk {

    private final List<Beverage> orderBeverages = new ArrayList<>();

    public void add(final Beverage beverage) {
        orderBeverages.add(beverage);
    }

    public void add(final Beverage beverage, final int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("음료는 1잔 이상 주문 가능합니다.");
        }
        for (int i = 0; i < count; i++) {
            orderBeverages.add(beverage);
        }
    }

    public void remove(final Beverage beverage) {
        orderBeverages.remove(beverage);
    }

    public void clear() {
        orderBeverages.clear();
    }

    public int calculateTotalPrice() {
        return orderBeverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }

    public Order createOrder() {
        return new Order(LocalDateTime.now(), orderBeverages);
    }

    public List<Beverage> getOrderBeverages() {
        return orderBeverages.stream()
                .toList();
    }

}
