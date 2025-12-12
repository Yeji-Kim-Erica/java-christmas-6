package christmas.model.domain;

public class Bill {
    private static final Menu PROMOTION_MENU = Menu.CHAMPAGNE;
    private static final int PROMOTION_MINIMUM_COST = 120000;

    private final VisitDate date;
    private final Order order;
    private final Integer totalCost;
    private final Integer promotionMenuAmount;
    private final Benefit benefit;
    private final Integer totalBenefitAmount;
    private final Integer totalPriceExpected;
    private final EventBadge badge;

    public Bill(VisitDate date, Order order, Benefit benefit, EventBadge badge) {
        this.date = date;
        this.order = order;
        int totalCost = order.calculateTotalCost();
        this.totalCost = totalCost;
        this.promotionMenuAmount = calculatePromotionMenuAmount(totalCost);
        this.benefit = benefit;
        this.totalBenefitAmount = benefit.getTotalBenefitAmount();
        this.totalPriceExpected = totalCost - benefit.getDiscountAmount();
        this.badge = badge;
    }

    private Integer calculatePromotionMenuAmount(int totalCost) {
        if (totalCost >= PROMOTION_MINIMUM_COST) {
            return 1;
        }
        return null;
    }
}
