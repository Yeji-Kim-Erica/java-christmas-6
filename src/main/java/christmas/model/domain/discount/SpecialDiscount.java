package christmas.model.domain.discount;

import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

import java.util.List;

public class SpecialDiscount implements DiscountPolicy {
    private static final List<Integer> SPECIAL_DISCOUNT_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    @Override
    public boolean isApplicable(VisitDate date, Order order) {
        return date.isIncluded(SPECIAL_DISCOUNT_DAYS);
    }

    @Override
    public int calculateDiscount(VisitDate date, Order order) {
        return SPECIAL_DISCOUNT_AMOUNT;
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.SPECIAL;
    }
}
