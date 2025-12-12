package christmas.model.domain;

public class Benefit {
    private static final Menu PROMOTION_MENU = Menu.CHAMPAGNE;
    private static final int PROMOTION_MINIMUM_COST = 120000;

    private final int christmasDDayDiscount;
    private final int dailyDiscount;
    private final int specialDiscount;
    private final int promotionAmount;

    private Benefit(int christmasDDayDiscount, int dailyDiscount, int specialDiscount, int promotionItemAmount) {
        this.christmasDDayDiscount = christmasDDayDiscount;
        this.dailyDiscount = dailyDiscount;
        this.specialDiscount = specialDiscount;
        this.promotionAmount = promotionItemAmount;
    }

    public static Benefit of(int totalCost, int christmasDDayDiscount, int dailyDiscount, int specialDiscount) {
        int promotionAmount = calculatePromotionAmount(totalCost);
        return new Benefit(christmasDDayDiscount, dailyDiscount, specialDiscount, promotionAmount);
    }

    public int getTotalBenefitAmount() {
        return christmasDDayDiscount + dailyDiscount + specialDiscount + promotionAmount;
    }

    public int getDiscountAmount() {
        return christmasDDayDiscount + dailyDiscount + specialDiscount;
    }

    public int getPromotionAmount() {
        return promotionAmount;
    }

    private static int calculatePromotionAmount(int totalCost) {
        if (totalCost >= PROMOTION_MINIMUM_COST) {
            return PROMOTION_MENU.calculatePrice(1);
        }
        return 0;
    }
}
