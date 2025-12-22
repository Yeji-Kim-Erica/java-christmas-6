package christmas.model.domain.discount;

import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

public interface DiscountPolicy {
    boolean isApplicable(VisitDate date, Order order);

    int calculateDiscount(VisitDate date, Order order);

    DiscountType getDiscountType();
}
