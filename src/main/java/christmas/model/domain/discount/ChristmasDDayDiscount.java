package christmas.model.domain.discount;

import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

public class ChristmasDDayDiscount implements DiscountPolicy {
    private static final int CHRISTMAS_DISCOUNT_START_DATE = 1;
    private static final int CHRISTMAS_DISCOUNT_END_DATE = 25;
    private static final int CHRISTMAS_BASE_DISCOUNT_AMOUNT = 1000;
    private static final int CHRISTMAS_ADDITIONAL_DISCOUNT_AMOUNT = 100;

    @Override
    public boolean isApplicable(VisitDate date, Order order) {
        return !date.hasPassed(CHRISTMAS_DISCOUNT_END_DATE);
    }

    @Override
    public int calculateDiscount(VisitDate date, Order order) {
        int discountAmount = CHRISTMAS_BASE_DISCOUNT_AMOUNT;
        discountAmount += CHRISTMAS_ADDITIONAL_DISCOUNT_AMOUNT * (date.daysPassed(CHRISTMAS_DISCOUNT_START_DATE));
        return discountAmount;
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.CHRISTMAS_D_DAY;
    }
}
