package christmas.model.domain;

import christmas.exception.ErrorMessage;

import static christmas.model.domain.MenuType.*;

public enum Menu {
    // 애피타이저
    SOUP(APPETIZER, "양송이수프", 6000),
    TAPAS(APPETIZER, "타파스", 5500),
    SALAD(APPETIZER, "시저샐러드", 8000),

    // 메인
    STEAK(MAIN, "티본스테이크", 55000),
    RIBS(MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25000),

    // 디저트
    CAKE(DESSERT, "초코케이크", 15000),
    ICE_CREAM(DESSERT, "아이스크림", 5000),

    // 음료
    COKE(DRINK, "제로콜라", 3000),
    WINE(DRINK, "레드와인", 60000),
    CHAMPAGNE(DRINK, "샴페인", 25000);

    private final MenuType menuType;
    private final String name;
    private final int price;

    Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Menu from(String name) {
        for (Menu menu : Menu.values()) {
            if (name.equals(menu.name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
    }

    public int calculatePrice(int quantity) {
        return this.price * quantity;
    }

    public boolean isTypeOf(MenuType menuType) {
        return menuType.equals(this.menuType);
    }
}
