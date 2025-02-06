package sample.cafekiosk.spring.api.controller.order.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderCreateRequest {

    private final List<String> productNumbers;

    public OrderCreateRequest() {
        this.productNumbers = new ArrayList<>();
    }

    @Builder
    private OrderCreateRequest(final List<String> productNumbers) {
        this.productNumbers = productNumbers;
    }

}
