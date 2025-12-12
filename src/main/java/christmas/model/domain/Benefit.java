package christmas.model.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Benefit {
    private static final Menu PROMOTION_MENU = Menu.CHAMPAGNE;
    private static final int PROMOTION_MINIMUM_COST = 120000;

    private final Map<Discount, Integer> benefits;

    private Benefit(int christmasDDayDiscount, VisitDate date, int dailyDiscount, int specialDiscount, int promotionItemAmount) {
        Map<Discount, Integer> benefits = new LinkedHashMap<>();
        if (christmasDDayDiscount > 0) {
            benefits.put(Discount.CHRISTMAS_D_DAY, christmasDDayDiscount);
        }
        if (dailyDiscount > 0) {
            Discount discount = getDailyDiscount(date);
            benefits.put(discount, dailyDiscount);
        }
        if (specialDiscount > 0) {
            benefits.put(Discount.SPECIAL, specialDiscount);
        }
        if (promotionItemAmount > 0) {
            benefits.put(Discount.PROMOTION, promotionItemAmount);
        }
        this.benefits = benefits;
    }

    public static Benefit of(int totalCost, int christmasDDayDiscount, VisitDate date, int dailyDiscount, int specialDiscount) {
        int promotionAmount = calculatePromotionAmount(totalCost);
        return new Benefit(christmasDDayDiscount, date, dailyDiscount, specialDiscount, promotionAmount);
    }

    public Set<Entry<Discount, Integer>> getBenefitDetails() {
        return Collections.unmodifiableSet(benefits.entrySet());
    }

    public int getTotalBenefitAmount() {
        int sum = 0;
        for (Entry<Discount, Integer> entry : benefits.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    public int getDiscountAmount() {
        int sum = 0;
        for (Entry<Discount, Integer> entry : benefits.entrySet()) {
            if (!Discount.PROMOTION.equals(entry.getKey())) {
                sum += entry.getValue();
            }
        }
        return sum;
    }

    public String getPromotionItem() {
        return PROMOTION_MENU.toString();
    }

    private static int calculatePromotionAmount(int totalCost) {
        if (totalCost >= PROMOTION_MINIMUM_COST) {
            return PROMOTION_MENU.calculatePrice(1);
        }
        return 0;
    }

    private Discount getDailyDiscount(VisitDate date) {
        if (date.isWeekend()) {
            return Discount.DAILY_WEEKEND;
        }
        return Discount.DAILY_WEEKDAY;
    }
}
