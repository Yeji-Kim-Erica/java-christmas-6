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
            // when
            Benefit benefit = Benefit.of(120000, 0, 0, 0);

            // then
            assertThat(benefit.getPromotionAmount()).isEqualTo(25000);
        }

        @DisplayName("총혜택 금액을 계산한다")
        @Test
        void should_calculateTotalBenefitAmount() {
            // when
            Benefit benefit = Benefit.of(120000, 1000, 1000, 1000);

            // then
            assertThat(benefit.getTotalBenefitAmount()).isEqualTo(28000);
        }
    }
}
