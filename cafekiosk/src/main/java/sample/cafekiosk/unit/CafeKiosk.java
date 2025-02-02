package sample.cafekiosk.unit;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);
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

    public Order createOrder(final LocalDateTime orderDateTime) {
        LocalTime orderTime = orderDateTime.toLocalTime();
        if (orderTime.isBefore(SHOP_OPEN_TIME) || orderTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에 문의하세요.");
        }
        return new Order(orderDateTime, orderBeverages);
    }

    public List<Beverage> getOrderBeverages() {
        return orderBeverages.stream()
                .toList();
    }

}
