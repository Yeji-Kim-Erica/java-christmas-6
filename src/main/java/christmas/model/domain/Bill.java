package christmas.model.domain;

public class Bill {
    private final VisitDate date;
    private final Order order;
    private final int totalCost;
    private final Benefit benefit;
    private final int totalBenefitAmount;
    private final int totalPriceExpected;
    private final EventBadge badge;

    public Bill(VisitDate date, Order order, int totalCost, Benefit benefit) {
        this.date = date;
        this.order = order;
        this.totalCost = totalCost;
        this.benefit = benefit;
        int totalBenefitAmount = calculateTotalBenefitAmount(benefit);
        this.totalBenefitAmount = totalBenefitAmount;
        this.totalPriceExpected = calculateTotalPriceExpected(totalCost, benefit);
        this.badge = awardBadge(totalBenefitAmount);
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
        if (benefit != null) {
            return benefit.getTotalBenefitAmount();
        }
        return 0;
    }

    private int calculateTotalPriceExpected(int totalCost, Benefit benefit) {
        int priceExpected = totalCost;
        if (benefit != null) {
            priceExpected -= benefit.getDiscountAmount();
        }
        return priceExpected;
    }

    private EventBadge awardBadge(int totalBenefitAmount) {
        return EventBadge.of(totalBenefitAmount);
    }
}
