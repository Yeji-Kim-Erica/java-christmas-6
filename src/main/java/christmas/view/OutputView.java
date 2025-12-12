package christmas.view;

import christmas.model.domain.Menu;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

import java.util.Map;
import java.util.Map.Entry;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    };

    public void printEventDetailInstruction(VisitDate date) {
        System.out.printf("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date.toString());
        printBlankLine();
    };

    public void printOrder(Order order) {
        printBlankLine();
        System.out.println("<주문 메뉴>");
        for (Entry<Menu, Integer> entry : order.getDetailEntrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%s %d개", menu.toString(), quantity);
            printBlankLine();
        }
    };

    private void printBlankLine() {
        System.out.println();
    }
}
