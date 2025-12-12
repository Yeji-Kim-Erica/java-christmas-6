package christmas.model.domain;

public enum Discount {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    DAILY_WEEKDAY("평일 할인"),
    DAILY_WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    PROMOTION("증정 이벤트");

    private final String name;

    Discount(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
