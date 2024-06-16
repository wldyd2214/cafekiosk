package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.IntegrationTestSupport;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

// profile 설정으로 테스트 코드가 동작 되도록 해준다.
//@ActiveProfiles("test")
//@SpringBootTest
// SpringBootTest 보다는 DataJpaTest 더 가볍기는 하지만
//@DataJpaTest
@Transactional
class ProductRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private ProductRepository productRepository;


    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING,"아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD,"카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> products =
                productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        // then
        assertThat(products)
                // 길이가 2개 인지
                .hasSize(2)
                // "productNumber", "name", "sellingStatus" 컬럼을 비교할 것 이다.
                .extracting("productNumber", "name", "sellingStatus")
                // 순서 상관없이 확인
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );

    }

    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    @Test
    void findAllByProductNumberIn() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING,"아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD,"카페라떼", 4500);
        Product product3 = createProduct("003", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> products =
                productRepository.findByProductNumberIn(List.of("001", "002"));

        // then
        assertThat(products)
                // 길이가 2개 인지
                .hasSize(2)
                // "productNumber", "name", "sellingStatus" 컬럼을 비교할 것 이다.
                .extracting("productNumber", "name", "sellingStatus")
                // 순서 상관없이 확인
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );

    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어온다.")
    @Test
    void findLatestProductNumber() {
        // given
        String targetProductNumber = "003";
        Product product1 = createProduct("001", HANDMADE, SELLING,"아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, HOLD,"카페라떼", 4500);
        Product product3 = createProduct(targetProductNumber, HANDMADE, STOP_SELLING, "팥빙수", 7000);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        String latestProductNumber = productRepository.findLatestProductNumber();

        // then
        assertThat(latestProductNumber).isEqualTo(targetProductNumber);
    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품번호를 읽어올 때, 상품이 하나도 없는 경우 null을 반환한다.")
    @Test
    void findLatestProductNumberWhenProductIsEmpty() {
        // when
        String latestProductNumber = productRepository.findLatestProductNumber();

        // then
        assertThat(latestProductNumber).isNull();
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, Integer price) {
        return Product.builder()
                      .productNumber(productNumber)
                      .type(type)
                      .sellingStatus(sellingStatus)
                      .name(name)
                      .price(price)
                      .build();
    }

}