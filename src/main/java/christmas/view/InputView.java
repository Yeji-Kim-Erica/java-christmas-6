package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;

/**
 * 프로그램의 모든 입력을 담당하는 클래스
 */
public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return parseToInt(input, ErrorMessage.INVALID_DATE.getMessage());
    }

    private int parseToInt(String input, String errorMessage) {
        String refinedInput = refineInput(input, errorMessage);
        try {
            return Integer.parseInt(refinedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private String refineInput(String input, String errorMessage) {
        boolean isNullOrBlank = (input == null) || input.isBlank();
        if (isNullOrBlank) {
            throw new IllegalArgumentException(errorMessage);
        }
        return input.trim();
    }
}
