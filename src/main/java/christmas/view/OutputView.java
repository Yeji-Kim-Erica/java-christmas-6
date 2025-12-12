package christmas.view;

import christmas.model.domain.Bill;
import christmas.model.domain.Menu;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;

import java.text.DecimalFormat;
import java.util.Map.Entry;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printBill(Bill bill) {
        printEventDetailInstruction(bill.getDate());
        printOrder(bill.getOrder());
        printTotalCost(bill.getTotalCost());
    }

    private void printBlankLine() {
        System.out.println();
    }

    private void printEventDetailInstruction(VisitDate date) {
        System.out.printf("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date.toString());
        printBlankLine();
    }

    private void printOrder(Order order) {
        printBlankLine();
        System.out.println("<주문 메뉴>");
        for (Entry<Menu, Integer> entry : order.getDetailEntrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%s %d개", menu.toString(), quantity);
            printBlankLine();
        }
    }

    private void printTotalCost(int totalCost) {
        printBlankLine();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%s원", DECIMAL_FORMAT.format(totalCost));
        printBlankLine();
    }
}
