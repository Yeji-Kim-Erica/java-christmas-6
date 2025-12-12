package christmas.model.service;

import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

public class DiscountService {
    private static final int MINIMUM_PRICE_FOR_DISCOUNT = 10000;

    private static final int CHRISTMAS_DISCOUNT_START_DATE = 1;
    private static final int CHRISTMAS_DISCOUNT_END_DATE = 25;
    private static final int CHRISTMAS_BASE_DISCOUNT_AMOUNT = 1000;
    private static final int CHRISTMAS_ADDITIONAL_DISCOUNT_AMOUNT = 100;

    public boolean isDiscountable(Order order) {
        int totalCost = order.calculateTotalCost();
        if (totalCost < MINIMUM_PRICE_FOR_DISCOUNT) {
            return false;
        }
        return true;
    }

    public int getChristmasDiscount(VisitDate date) {
        if (date.hasPassed(CHRISTMAS_DISCOUNT_END_DATE)) {
            return 0;
        }
        int discountAmount = CHRISTMAS_BASE_DISCOUNT_AMOUNT;
        discountAmount += CHRISTMAS_ADDITIONAL_DISCOUNT_AMOUNT * (date.daysPassed(CHRISTMAS_DISCOUNT_START_DATE));
        return discountAmount;
    }
}
