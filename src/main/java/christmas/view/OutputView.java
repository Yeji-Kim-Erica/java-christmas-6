package christmas.view;

import christmas.model.domain.*;

import java.text.DecimalFormat;
import java.util.Map.Entry;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
    private static final String NONE = "없음";

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
        printPromotionItem(bill.getBenefit());
        printBenefit(bill.getBenefit());
    }

    private void printBlankLine() {
        System.out.println();
    }

    public void printEventDetailInstruction(VisitDate date) {
        System.out.printf("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date.toString());
        printBlankLine();
    }

    public void printOrder(Order order) {
        printBlankLine();
        System.out.println("<주문 메뉴>");
        for (Entry<Menu, Integer> entry : order.getDetailEntrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%s %d개", menu, quantity);
            printBlankLine();
        }
    }

    public void printTotalCost(int totalCost) {
        printBlankLine();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%s원", DECIMAL_FORMAT.format(totalCost));
        printBlankLine();
    }

    public void printPromotionItem(Benefit benefit) {
        printBlankLine();
        System.out.println("<증정 메뉴>");
        System.out.printf("%s", getPromotionItem(benefit));
        printBlankLine();
    }

    public String getPromotionItem(Benefit benefit) {
        if (benefit != null) {
            return String.format("%s 1개", benefit.getPromotionItem());
        }
        return NONE;
    }

    public void printBenefit(Benefit benefit) {
        printBlankLine();
        System.out.println("<혜택 내역>");
        printBenefitDetails(benefit);
    }

    public void printBenefitDetails(Benefit benefit) {
        if (benefit == null) {
            System.out.println(NONE);
            return;
        }
        for (Entry<Discount, Integer> detail : benefit.getBenefitDetails()) {
            String amount = DECIMAL_FORMAT.format(detail.getValue());
            System.out.printf("%s: -%s원", detail.getKey(), amount);
            printBlankLine();
        }
    }
}
