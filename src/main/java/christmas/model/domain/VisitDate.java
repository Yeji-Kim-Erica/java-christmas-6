package christmas.model.domain;

import christmas.exception.ErrorMessage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class VisitDate {
    private static final int year = 2023;
    private static final Month month = Month.DECEMBER;

    private final int date;

    public VisitDate(int date) {
        validate(date);
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

    public boolean isIncluded(List<Integer> dates) {
        return dates.contains(this.date);
    }

    @Override
    public String toString() {
        return String.valueOf(date);
    }

    private static void validate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
