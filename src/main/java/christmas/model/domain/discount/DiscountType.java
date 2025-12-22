package christmas.model.domain.discount;

import christmas.model.domain.Menu;

public enum DiscountType {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", null),
    DAILY_WEEKDAY("평일 할인", null),
    DAILY_WEEKEND("주말 할인", null),
    SPECIAL("특별 할인", null),
    PROMOTION("증정 이벤트", Menu.CHAMPAGNE);

    private final String name;
    private final Menu promotionMenu;

    DiscountType(String name, Menu promotionMenu) {
        this.name = name;
        this.promotionMenu = promotionMenu;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isPromotion() {
        return promotionMenu != null;
    }

    public Menu getPromotionMenu() {
        return promotionMenu;
    }
}
