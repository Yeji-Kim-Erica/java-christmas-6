package christmas.controller;

import christmas.model.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

/**
 * 프로그램의 전체 흐름 조율을 담당하는 클래스
 */
public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printHello();
        try {
            VisitDate date = new VisitDate(inputView.readDate());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }
}
