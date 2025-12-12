package christmas.model.domain;

import christmas.model.constants.MenuType;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Order {
    private final Map<Menu, Integer> detail;

    private Order(Map<Menu, Integer> detail) {
        this.detail = detail;
    }

    public static Order of(Map<String, Integer> input) {
        Map<Menu, Integer> detail = new HashMap<>();
        Set<Entry<String, Integer>> set = input.entrySet();
        for (Entry<String, Integer> entry : set) {
            String menuName = entry.getKey();
            Menu menu = Menu.from(menuName);
            int quantity = entry.getValue();
            detail.put(menu , quantity);
        }
        return new Order(detail);
    }

    public int calculateTotalCost() {
        int totalCost = 0;
        Set<Entry<Menu, Integer>> set = this.detail.entrySet();
        for (Entry<Menu, Integer> entry : set) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            totalCost += menu.calculatePrice(quantity);
        }
        return totalCost;
    }

    public int getAmountByType(MenuType menuType) {
        int amount = 0;
        Set<Entry<Menu, Integer>> set = this.detail.entrySet();
        for (Entry<Menu, Integer> entry : set) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            if (menu.isTypeOf(menuType)) {
                amount += quantity;
            }
        }
        return amount;
    }
}
