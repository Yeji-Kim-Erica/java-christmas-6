package christmas.model.service;

import christmas.model.domain.Benefit;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;
import christmas.model.domain.discount.DiscountPolicy;
import christmas.model.domain.discount.DiscountType;

import java.util.*;

public class DiscountService {
    private static final int MINIMUM_PRICE_FOR_DISCOUNT = 10000;

    private final List<DiscountPolicy> discountPolicies;

    public DiscountService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public Benefit createBenefit(VisitDate date, Order order) {
        if (!isDiscountable(order)) {
            return Benefit.EMPTY;
        }
        Map<DiscountType, Integer> benefits = new EnumMap<>(DiscountType.class);
        for (DiscountPolicy discountPolicy : discountPolicies) {
            if (!discountPolicy.isApplicable(date, order)) {
                continue;
            }
            int discountAmount = discountPolicy.calculateDiscount(date, order);
            if (discountAmount > 0) {
                benefits.put(discountPolicy.getDiscountType(), discountAmount);
            }
        }
        if (benefits.isEmpty()) {
            return Benefit.EMPTY;
        }
        return new Benefit(benefits);
    }

    private boolean isDiscountable(Order order) {
        int totalCost = order.calculateTotalCost();
        return totalCost >= MINIMUM_PRICE_FOR_DISCOUNT;
    }
}
