package christmas.model.domain;

public class VisitDate {
    private final int date;

    public VisitDate(int date) {
        this.date = date;
    }

    public boolean hasPassed(int baseDate) {
        return baseDate < this.date;
    }

    public int daysPassed(int baseDate) {
        int passedDays = this.date - baseDate;
        return Math.max(passedDays, 0);
    }
}
