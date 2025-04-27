package sample.cafekiosk.spring.domain.product;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    HANDMADE("제조 음료"),
    BOTTLE("병 음료"),
    BAKERY("베이커리");

    private final String text;

    public static boolean containsStockType(final ProductType type) {
        List<ProductType> stockType = List.of(BOTTLE, BAKERY);
        return stockType.contains(type);
    }
}
