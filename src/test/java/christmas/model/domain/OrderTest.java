package christmas.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
    @Nested
    class SuccessTest {
        @DisplayName("총주문 금액을 계산한다")
        @Test
        void should_calculate_totalCost() {
            // given
            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("제로콜라", 1);

            // when
            Order order = Order.of(detail);

            // then
            assertThat(order.calculateTotalCost()).isEqualTo(64000);
        }
    }
}
