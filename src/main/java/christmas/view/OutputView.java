package christmas.view;

import christmas.model.domain.*;

import java.util.Map.Entry;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    private static final String NONE = "없음";

    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
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
        System.out.printf("%,d원", totalCost);
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

    public void printTotalBenefitAmount(Benefit benefit) {
        printBlankLine();
        System.out.println("<총혜택 금액>");
        if (benefit == null) {
            System.out.println(NONE);
            return;
        }
        System.out.printf("-%,d원", benefit.getTotalBenefitAmount());
        printBlankLine();
    }

    private void printBenefitDetails(Benefit benefit) {
        if (benefit == null) {
            System.out.println(NONE);
            return;
        }
        for (Entry<Discount, Integer> detail : benefit.getBenefitDetails()) {
            System.out.printf("%s: -%,d원", detail.getKey(), detail.getValue());
            printBlankLine();
        }
    }

    private void printBlankLine() {
        System.out.println();
    }
}
