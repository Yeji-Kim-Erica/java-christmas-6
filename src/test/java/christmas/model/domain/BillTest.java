package christmas.model.domain;

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
            int totalCost = order.calculateTotalCost();
            Benefit benefit = Benefit.of(totalCost, 1000, 1000, 1000);

            // when
            Bill bill = new Bill(date, order, benefit);

            // then
            assertThat(bill.getTotalPriceExpected()).isEqualTo(152000);
        }
    }
}
