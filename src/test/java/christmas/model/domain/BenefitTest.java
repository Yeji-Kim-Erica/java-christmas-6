package christmas.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitTest {
    @Nested
    class SuccessTest {
        @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때 증정 이벤트가 발생한다")
        @Test
        void should_GiveawayPromotionMenu_WhenTotalCostIsMinimumOrMore() {
            // given
            VisitDate date = new VisitDate(5);
            Map<String, Integer> detail = new HashMap<>();
            detail.put("티본스테이크", 4);
            Order order = Order.of(detail);
            int totalCost = order.calculateTotalCost();

            // when
            Benefit benefit = Benefit.of(totalCost, 0, 0, 0);

            // then
            assertThat(benefit.getPromotionAmount()).isEqualTo(25000);
        }
    }
}
