package christmas.model.service;

import christmas.model.domain.Order;

public class CalculateService {
    private static final int MINIMUM_PRICE = 10000;

    public int calculateDiscountAmount(Order order) {
        int totalCost = order.calculateTotalCost();
        if (totalCost < MINIMUM_PRICE) {
            return 0;
        }
        int discountPrice = 0;
        return discountPrice;
    }
}
