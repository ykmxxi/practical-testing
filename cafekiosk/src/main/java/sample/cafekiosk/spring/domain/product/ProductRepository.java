package sample.cafekiosk.spring.domain.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select *
     * from product
     * where selling_status in ('SELLING', 'HOLD');
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingTypes);

    /**
     * select *
     * from product
     * where product_number in ('001', '002');
     */
    List<Product> findAllByProductNumberIn(List<String> productNumbers);

}
