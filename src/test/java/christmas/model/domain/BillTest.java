package christmas.model.domain;

import christmas.model.domain.discount.DiscountType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BillTest {
    @Nested
    class SuccessTest {
        @DisplayName("할인 후 예상 결제 금액을 계산한다")
        @Test
        void should_calculateExpectedPaymentAmount() {
            // given
            VisitDate date = new VisitDate(5);

            Map<String, Integer> detail = new HashMap<>();
            detail.put("크리스마스파스타", 4);
            detail.put("티본스테이크", 1);
            Order order = Order.of(detail);

            int totalCost = Menu.CHRISTMAS_PASTA.calculatePrice(4) + Menu.STEAK.calculatePrice(1);
            int discountAmount = 2023*6;

            Map<DiscountType, Integer> discounts = new HashMap<>();
            discounts.put(DiscountType.DAILY_WEEKDAY, discountAmount);
            discounts.put(DiscountType.PROMOTION, DiscountType.PROMOTION.getPromotionMenu().calculatePrice(1));
            Benefit benefit = new Benefit(discounts);

            // when
            Bill bill = new Bill(date, order, benefit);
            int expected = totalCost - discountAmount;

            // then
            assertThat(bill.getTotalPriceExpected()).isEqualTo(expected);
        }
    }
}
