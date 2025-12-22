package christmas.model.domain.discount;

import christmas.model.domain.MenuType;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

public class WeekdayDiscount implements DiscountPolicy {
    private static final MenuType WEEKDAY_DISCOUNT_MENU_TYPE = MenuType.DESSERT;
    private static final int WEEKDAY_BASE_DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isApplicable(VisitDate date, Order order) {
        return !date.isWeekend();
    }

    @Override
    public int calculateDiscount(VisitDate date, Order order) {
        int menuAmount = order.getAmountByType(WEEKDAY_DISCOUNT_MENU_TYPE);
        return WEEKDAY_BASE_DISCOUNT_AMOUNT * menuAmount;
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.DAILY_WEEKDAY;
    }
}
