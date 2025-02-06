package sample.cafekiosk.spring.api.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.HOLD;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.STOP_SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.orderproduct.OrderProductRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@ActiveProfiles(value = "test")
@SpringBootTest
class OrderServiceTest {

    @Autowired private ProductRepository productRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderProductRepository orderProductRepository;
    @Autowired private OrderService orderService;

    @AfterEach
    void tearDown() {
//      엔티티를 하나씩 조회한 후 개별적으로 삭제
//      내부적으로는 각 엔티티를 영속성 컨텍스트에서 관리하면서 EntityManager.remove(entity)를 반복적으로 호출
//      productRepository.deleteAll();

//      한 번의 SQL 쿼리로 모든 데이터를 삭제하는 배치 삭제(batch delete) 기능을 제공
//      내부적으로 EntityManager.createQuery("DELETE FROM Entity")를 실행하여 직접 DELETE 쿼리를 수행
        orderProductRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
    }

    @DisplayName("주문상품번호 리스트를 받아 주문을 생성한다.")
    @Test
    void createOrder() {
        // given
        Product americano = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product latte = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product redBeanIced = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(americano, latte, redBeanIced));
        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "002"))
                .build();
        LocalDateTime registeredDateTime = LocalDateTime.now();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse).extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 8500);
        assertThat(orderResponse.getProducts())
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 4000),
                        tuple("002", 4500)
                );
    }

    @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 수 있다.")
    @Test
    void createOrderWithDuplicateProductsNumbers() {
        // given
        Product americano = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product latte = createProduct("002", HANDMADE, HOLD, "카페라떼", 4500);
        Product redBeanIced = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(americano, latte, redBeanIced));
        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "001"))
                .build();
        LocalDateTime registeredDateTime = LocalDateTime.now();

        // when
        OrderResponse orderResponse = orderService.createOrder(request, registeredDateTime);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse).extracting("registeredDateTime", "totalPrice")
                .contains(registeredDateTime, 8000);
        assertThat(orderResponse.getProducts())
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 4000),
                        tuple("001", 4000)
                );
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus,
                                  String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }

}
