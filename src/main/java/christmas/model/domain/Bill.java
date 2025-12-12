package christmas.model.domain;

public class Bill {
    private final VisitDate date;
    private final Order order;
    private final Integer totalCost;
    private final Benefit benefit;
    private final Integer totalBenefitAmount;
    private final Integer totalPriceExpected;
    private final EventBadge badge;

    public Bill(VisitDate date, Order order, Benefit benefit) {
        this.date = date;
        this.order = order;
        int totalCost = order.calculateTotalCost();
        this.totalCost = totalCost;
        this.benefit = benefit;
        int totalBenefitAmount = benefit.getTotalBenefitAmount();
        this.totalBenefitAmount = totalBenefitAmount;
        this.totalPriceExpected = totalCost - benefit.getDiscountAmount();
        this.badge = awardBadge(totalBenefitAmount);
    }

    public int getTotalPriceExpected() {
        return totalPriceExpected;
    }

    private EventBadge awardBadge(int totalBenefitAmount) {
        return null;
    }
}
