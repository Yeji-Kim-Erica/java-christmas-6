package christmas.controller;

import christmas.model.domain.Benefit;
import christmas.model.domain.Bill;
import christmas.model.domain.Order;
import christmas.model.domain.VisitDate;
import christmas.model.service.DiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.function.Supplier;

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
        VisitDate date = retryUntilSuccess(() -> new VisitDate(inputView.readDate()));
        Order order = retryUntilSuccess(() -> Order.of(inputView.readOrder()));
        Bill bill = createBill(date, order);
        outputView.printBill(bill);
    }

    private <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private Bill createBill(VisitDate date, Order order) {
        Benefit benefit = discountService.createBenefit(date, order);;
        return new Bill(date, order, benefit);
    }
}
