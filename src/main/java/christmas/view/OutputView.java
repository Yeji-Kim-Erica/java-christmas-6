package christmas.view;

import christmas.model.domain.*;
import org.mockito.internal.matchers.Or;

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

    public void printBill(Bill bill) {
        printEventDetailInstruction(bill.getDate());
        printOrder(bill.getOrder());
        printTotalCost(bill.getTotalCost());
        Benefit benefit = bill.getBenefit();
        printPromotionItem(benefit);
        printBenefit(benefit);
        printTotalBenefitAmount(bill.getTotalBenefitAmount());
        printExpectedPaymentPrice(bill.getTotalPriceExpected());
        printEventBadge(bill.getBadge());
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
            System.out.printf("%s %d개", menu, quantity);
            printBlankLine();
        }
    }

    private void printTotalCost(int totalCost) {
        printBlankLine();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원", totalCost);
        printBlankLine();
    }

    private void printPromotionItem(Benefit benefit) {
        printBlankLine();
        System.out.println("<증정 메뉴>");
        System.out.printf("%s", getPromotionItem(benefit));
        printBlankLine();
    }

    private String getPromotionItem(Benefit benefit) {
        if (benefit != null && benefit.getPromotionItem() != null) {
            return String.format("%s 1개", benefit.getPromotionItem());
        }
        return NONE;
    }

    private void printBenefit(Benefit benefit) {
        printBlankLine();
        System.out.println("<혜택 내역>");
        printBenefitDetails(benefit);
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

    private void printTotalBenefitAmount(int amount) {
        printBlankLine();
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원", amount * (-1));
        printBlankLine();
    }

    private void printExpectedPaymentPrice(int expectedPrice) {
        printBlankLine();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원", expectedPrice);
        printBlankLine();
    }

    private void printEventBadge(EventBadge badge) {
        printBlankLine();
        System.out.println("<12월 이벤트 배지>");
        if (badge == null) {
            System.out.println(NONE);
            return;
        }
        System.out.println(badge);
    }
}
