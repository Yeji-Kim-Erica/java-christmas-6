package christmas.model.domain;

public class Bill {
    private final VisitDate date;
    private final Order order;
    private final int totalCost;
    private final Benefit benefit;
    private final int totalBenefitAmount;
    private final int totalPriceExpected;
    private final EventBadge badge;

    public Bill(VisitDate date, Order order, Benefit benefit) {
        this.date = date;
        this.order = order;
        this.totalCost = order.calculateTotalCost();
        this.benefit = benefit;
        this.totalBenefitAmount = calculateTotalBenefitAmount(benefit);
        this.totalPriceExpected = calculateTotalPriceExpected(totalCost, benefit);
        this.badge = benefit.awardBadge();
    }

    public VisitDate getDate() {
        return date;
    }

    public Order getOrder() {
        return order;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public Benefit getBenefit() {
        return benefit;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public int getTotalPriceExpected() {
        return totalPriceExpected;
    }

    public EventBadge getBadge() {
        return badge;
    }

    private int calculateTotalBenefitAmount(Benefit benefit) {
        return benefit.getTotalBenefitAmount();
    }

    private int calculateTotalPriceExpected(int totalCost, Benefit benefit) {
        return totalCost - benefit.getTotalDiscountAmount();
    }
}
