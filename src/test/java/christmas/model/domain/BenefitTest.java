package christmas.model.domain;

import christmas.model.domain.discount.DiscountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitTest {
    private static final int PROMOTION_PRICE = DiscountType.PROMOTION.getPromotionMenu().calculatePrice(1);

    private Benefit benefit;

    @BeforeEach
    void setUp() {
        Map<DiscountType, Integer> discounts = new HashMap<>();
        discounts.put(DiscountType.CHRISTMAS_D_DAY, 1000);
        discounts.put(DiscountType.DAILY_WEEKEND, 2023);
        discounts.put(DiscountType.PROMOTION, PROMOTION_PRICE);
        benefit = new Benefit(discounts);
    }

    @Nested
    class SuccessTest {
        @DisplayName("총혜택 금액을 계산한다")
        @Test
        void should_calculateTotalBenefitAmount() {
            assertThat(benefit.getTotalBenefitAmount()).isEqualTo(1000 + 2023 + PROMOTION_PRICE);
        }

        @DisplayName("증정품 이벤트를 제외한 총할인 금액을 계산한다")
        @Test
        void should_calculateTotalDiscountAmount() {
            assertThat(benefit.getTotalDiscountAmount()).isEqualTo(1000 + 2023);
        }
    }
}
