package christmas.model.service;

import christmas.model.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateServiceTest {
    private CalculateService service;

    @BeforeEach
    void setUp() {
        service = new CalculateService();
    }

    @Nested
    class SuccessTest {
        @DisplayName("총주문 금액이 최소 기준을 넘기지 못하면 할인 이벤트를 적용하지 않는다")
        @Test
        void should_calculate_totalCost_withOutDiscount() {
            // given
            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("제로콜라", 1);

            // when
            Order order = Order.of(detail);

            // then
            assertThat(service.calculateDiscountAmount(order)).isEqualTo(0);
        }
    }
}
