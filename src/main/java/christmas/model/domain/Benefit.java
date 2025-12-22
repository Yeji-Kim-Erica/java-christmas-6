package christmas.model.domain;

import christmas.model.domain.discount.DiscountType;

import java.util.*;
import java.util.Map.Entry;

public class Benefit {
    public static final Benefit EMPTY = new Benefit(new EnumMap<>(DiscountType.class));

    private final Map<DiscountType, Integer> benefits;

    public Benefit(Map<DiscountType, Integer> benefits) {
        this.benefits = new EnumMap<>(benefits);
    }

    public Set<Entry<DiscountType, Integer>> getBenefitDetails() {
        return Collections.unmodifiableSet(benefits.entrySet());
    }

    public boolean isEmpty() {
        return benefits.isEmpty();
    }

    public Integer getDiscountAmount(DiscountType discountType) {
        return benefits.get(discountType);
    }

    public int getTotalBenefitAmount() {
        int sum = 0;
        for (Entry<DiscountType, Integer> entry : benefits.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    public int getTotalDiscountAmount() {
        int sum = 0;
        for (Entry<DiscountType, Integer> entry : benefits.entrySet()) {
            if (!entry.getKey().isPromotion()) {
                sum += entry.getValue();
            }
        }
        return sum;
    }

    public Menu getPromotionItem() {
        for (Entry<DiscountType, Integer> entry : benefits.entrySet()) {
            DiscountType discountType = entry.getKey();
            if (discountType.isPromotion()) {
                return discountType.getPromotionMenu();
            }
        }
        return null;
    }

    public EventBadge awardBadge() {
        int totalBenefitAmount= getTotalBenefitAmount();
        return EventBadge.of(totalBenefitAmount);
    }
}
