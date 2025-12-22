package christmas.model.domain.discount;

import christmas.model.domain.Benefit;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountPolicyTest {
    private final VisitDate date = new VisitDate(5);
    private Order order = null;

    @BeforeEach
    void setUp() {
        Map<String, Integer> detail = new HashMap<>();
        detail.put("양송이수프", 1);
        detail.put("티본스테이크", 1);
        detail.put("제로콜라", 1);

        order = Order.of(detail);
    }

    @Nested
    class SuccessTest {
        @DisplayName("크리스마스 할인 금액을 계산한다")
        @Test
        void should_Calculate_ChristmasDiscountAmount() {
            // given
            ChristmasDDayDiscount christmasDDayDiscount = new ChristmasDDayDiscount();

            // when & then
            assertThat(christmasDDayDiscount.isApplicable(date, order)).isTrue();
            assertThat(christmasDDayDiscount.calculateDiscount(date, order)).isEqualTo(1400);
        }

        @DisplayName("평일 할인 금액을 계산한다")
        @Test
        void should_Calculate_WeekdayDiscount() {
            // given
            WeekdayDiscount weekdayDiscount = new WeekdayDiscount();

            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("초코케이크", 2);
            detail.put("아이스크림", 4);
            Order order = Order.of(detail);

            // when & then
            assertThat(weekdayDiscount.isApplicable(date, order)).isTrue();
            assertThat(weekdayDiscount.calculateDiscount(date, order)).isEqualTo(2023*6);
        }

        @DisplayName("주말 할인 금액을 계산한다")
        @Test
        void should_Calculate_WeekendDiscount() {
            // given
            WeekendDiscount weekendDiscount = new WeekendDiscount();

            VisitDate date = new VisitDate(8);

            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("바비큐립", 2);
            detail.put("해산물파스타", 2);
            Order order = Order.of(detail);

            // when & then
            assertThat(weekendDiscount.isApplicable(date, order)).isTrue();
            assertThat(weekendDiscount.calculateDiscount(date, order)).isEqualTo(2023*5);
        }

        @DisplayName("특별 할인 금액을 계산한다")
        @Test
        void should_Calculate_SpecialDiscount() {
            // given
            SpecialDiscount specialDiscount = new SpecialDiscount();

            VisitDate specialDate = new VisitDate(10);
            VisitDate ordinaryDate = new VisitDate(11);

            // when & then
            assertThat(specialDiscount.calculateDiscount(specialDate, order)).isEqualTo(1000);
            assertThat(specialDiscount.calculateDiscount(ordinaryDate, order)).isEqualTo(0);
        }

        @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때 증정 이벤트가 발생한다")
        @Test
        void should_GiveawayPromotionMenu_WhenTotalCostIsMinimumOrMore() {
            // given
            PromotionDiscount promotionDiscount = new PromotionDiscount();

            Map<String, Integer> detail = new HashMap<>();
            detail.put("양송이수프", 1);
            detail.put("티본스테이크", 1);
            detail.put("바비큐립", 2);
            detail.put("해산물파스타", 2);
            Order order = Order.of(detail);

            int promotionAmount = DiscountType.PROMOTION.getPromotionMenu().calculatePrice(1);

            // then
            assertThat(promotionDiscount.isApplicable(date, order)).isTrue();
            assertThat(promotionDiscount.calculateDiscount(date, order)).isEqualTo(promotionAmount);
        }
    }
}
