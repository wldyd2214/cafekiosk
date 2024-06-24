package sample.cafekiosk.spring.domain.orderproduct;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.BaseEntity;
import sample.cafekiosk.spring.domain.product.Product;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 데이터가 꼭 필요한 시점에 쿼리가 되도록 설정
    // fetch = FetchType.LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    // 데이터가 꼭 필요한 시점에 쿼리가 되도록 설정
    // fetch = FetchType.LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

}
