package christmas.model.service;

import christmas.model.constants.MenuType;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

import java.time.DayOfWeek;
import java.util.List;

public class DiscountService {
    private static final int MINIMUM_PRICE_FOR_DISCOUNT = 10000;

    // Christmas Discount
    private static final int CHRISTMAS_DISCOUNT_START_DATE = 1;
    private static final int CHRISTMAS_DISCOUNT_END_DATE = 25;
    private static final int CHRISTMAS_BASE_DISCOUNT_AMOUNT = 1000;
    private static final int CHRISTMAS_ADDITIONAL_DISCOUNT_AMOUNT = 100;

    // Daily Discount
    private static final MenuType WEEKDAY_DISCOUNT_MENU_TYPE = MenuType.DESSERT;
    private static final int WEEKDAY_BASE_DISCOUNT_AMOUNT = 2023;
    private static final MenuType WEEKEND_DISCOUNT_MENU_TYPE = MenuType.MAIN;
    private static final int WEEKEND_BASE_DISCOUNT_AMOUNT = 2023;

    // Special Discount
    private static final List<Integer> SPECIAL_DISCOUNT_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

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

    public int getDailyDiscount(VisitDate date, Order order) {
        if (date.isWeekend()) {
            return getWeekendDiscount(order);
        }
        return getWeekdayDiscount(order);
    }

    public int getSpecialDiscountAmount(VisitDate date) {
        if (date.isIncluded(SPECIAL_DISCOUNT_DAYS)) {
            return SPECIAL_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    private int getWeekendDiscount(Order order) {
        int menuAmount = order.getAmountByType(WEEKEND_DISCOUNT_MENU_TYPE);
        return WEEKEND_BASE_DISCOUNT_AMOUNT * menuAmount;
    }

    private int getWeekdayDiscount(Order order) {
        int menuAmount = order.getAmountByType(WEEKDAY_DISCOUNT_MENU_TYPE);
        return WEEKDAY_BASE_DISCOUNT_AMOUNT * menuAmount;
    }
}
