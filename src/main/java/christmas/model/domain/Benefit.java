package christmas.model.domain;

public class Benefit {
    private final int christmasDDayDiscount;
    private final int dailyDiscount;
    private final int specialDiscount;
    private final int promotionDiscount;

    public Benefit(int christmasDDayDiscount, int dailyDiscount, int specialDiscount, int promotionDiscount) {
        this.christmasDDayDiscount = christmasDDayDiscount;
        this.dailyDiscount = dailyDiscount;
        this.specialDiscount = specialDiscount;
        this.promotionDiscount = promotionDiscount;
    }

    public int getTotalBenefitAmount() {
        return christmasDDayDiscount + dailyDiscount + specialDiscount + promotionDiscount;
    }

    public int getDiscountAmount() {
        return christmasDDayDiscount + dailyDiscount + specialDiscount + promotionDiscount;
    }
}
