package christmas.model.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class VisitDate {
    private static final int year = 2023;
    private static final Month month = Month.DECEMBER;

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

    public DayOfWeek getDayOfWeek() {
        LocalDate localDate = LocalDate.of(year, month, this.date);
        return DayOfWeek.from(localDate);
    }
}
