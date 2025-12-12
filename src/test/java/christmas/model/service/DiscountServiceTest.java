package christmas.model.service;

import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountServiceTest {
    private DiscountService service;

    @BeforeEach
    void setUp() {
        service = new DiscountService();
    }

    @Nested
    class SuccessTest {
        @DisplayName("총주문 금액이 최소 기준(10,000원)을 넘기지 못하면 할인 이벤트를 적용하지 않는다")
        @Test
        void should_ReturnFalse_ForTotalCostLessThanMinimum() {
            // given
            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("제로콜라", 1);

            // when
            Order order = Order.of(detail);

            // then
            assertThat(service.isDiscountable(order)).isEqualTo(false);
        }

        @DisplayName("크리스마스 할인 금액을 계산한다")
        @Test
        void should_Calculate_ChristmasDiscountAmount() {
            // given
            VisitDate date = new VisitDate(5);

            // when & then
            assertThat(service.getChristmasDiscount(date)).isEqualTo(1400);
        }

        @DisplayName("평일 할인 금액을 계산한다")
        @Test
        void should_Calculate_WeekdayDiscount() {
            // given
            VisitDate date = new VisitDate(5);

            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("초코케이크", 2);
            detail.put("아이스크림", 4);
            Order order = Order.of(detail);

            // when & then
            assertThat(service.getDailyDiscount(date, order)).isEqualTo(2023*6);
        }
    }
}
