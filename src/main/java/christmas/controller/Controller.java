package christmas.controller;

import christmas.model.domain.Benefit;
import christmas.model.domain.Bill;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;
import christmas.model.service.DiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;

/**
 * 프로그램의 전체 흐름 조율을 담당하는 클래스
 */
public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final DiscountService discountService;

    public Controller(InputView inputView, OutputView outputView, DiscountService discountService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.discountService = discountService;
    }

    public void run() {
        outputView.printHello();
        try {
            VisitDate date = new VisitDate(inputView.readDate());
            Order order = Order.of(inputView.readOrder());
            Bill bill = createBill(date, order);
            outputView.printBill(bill);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }

    private Bill createBill(VisitDate date, Order order) {
        int totalCost = order.calculateTotalCost();
        int christmasDDayDiscount = discountService.getChristmasDiscount(date);
        int dailyDiscount = discountService.getDailyDiscount(date, order);
        int specialDiscount = discountService.getSpecialDiscountAmount(date);
        Benefit benefit = null;
        if (discountService.isDiscountable(order)) {
            benefit = Benefit.of(totalCost, christmasDDayDiscount, date, dailyDiscount, specialDiscount);
        }
        return new Bill(date, order, totalCost, benefit);
    }
}
