package christmas.model.domain.discount;

import christmas.model.domain.MenuType;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

public class WeekendDiscount implements DiscountPolicy {
    private static final MenuType WEEKEND_DISCOUNT_MENU_TYPE = MenuType.MAIN;
    private static final int WEEKEND_BASE_DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isApplicable(VisitDate date, Order order) {
        return date.isWeekend();
    }

    @Override
    public int calculateDiscount(VisitDate date, Order order) {
        int menuAmount = order.getAmountByType(WEEKEND_DISCOUNT_MENU_TYPE);
        return WEEKEND_BASE_DISCOUNT_AMOUNT * menuAmount;
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.DAILY_WEEKEND;
    }
}
