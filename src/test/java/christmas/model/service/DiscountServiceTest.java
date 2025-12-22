package christmas.model.service;

import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;
import christmas.model.domain.discount.ChristmasDDayDiscount;
import christmas.model.domain.discount.SpecialDiscount;
import christmas.model.domain.discount.WeekdayDiscount;
import christmas.model.domain.discount.WeekendDiscount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountServiceTest {
    private DiscountService service;

    @BeforeEach
    void setUp() {
        service = new DiscountService(List.of(
                new ChristmasDDayDiscount(),
                new WeekdayDiscount(),
                new WeekendDiscount(),
                new SpecialDiscount()
        ));
    }

    @Nested
    class SuccessTest {
        @DisplayName("총주문 금액이 최소 기준(10,000원)을 넘기지 못하면 할인 이벤트를 적용하지 않는다")
        @Test
        void should_ReturnEmptyBenefit_ForTotalCostLessThanMinimum() {
            // given
            VisitDate date = new VisitDate(5);

            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("제로콜라", 1);
            Order order = Order.of(detail);

            // when & then
            assertThat(service.createBenefit(date, order).isEmpty()).isTrue();
        }

        @DisplayName("총주문 금액이 최소 기준(10,000원)을 넘기면 할인 이벤트를 적용한다")
        @Test
        void should_CreateBenefit_ForTotalCostOverMinimum() {
            // given
            VisitDate date = new VisitDate(5);

            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("제로콜라", 1);
            Order order = Order.of(detail);

            // when & then
            assertThat(service.createBenefit(date, order).isEmpty()).isFalse();
        }
    }
}
