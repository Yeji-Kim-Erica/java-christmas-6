package christmas.model.domain.discount;

import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

public class PromotionDiscount implements DiscountPolicy {
    private static final int PROMOTION_MINIMUM_COST = 120000;
    private static final DiscountType DISCOUNT_TYPE = DiscountType.PROMOTION;

    @Override
    public boolean isApplicable(VisitDate date, Order order) {
        int totalCost = order.calculateTotalCost();
        return totalCost >= PROMOTION_MINIMUM_COST;
    }

    @Override
    public int calculateDiscount(VisitDate date, Order order) {
        return DISCOUNT_TYPE.getPromotionMenu().calculatePrice(1);
    }

    @Override
    public DiscountType getDiscountType() {
        return DISCOUNT_TYPE;
    }
}
