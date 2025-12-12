package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 프로그램의 모든 입력을 담당하는 클래스
 */
public class InputView {
    private static final Pattern PATTERN = Pattern.compile("(.+)-(d+),*");

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return parseToDate(input, ErrorMessage.INVALID_DATE.getMessage());
    }

    public Map<String, Integer> readOrder() {
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        return parseToOrder(input, ErrorMessage.INVALID_ORDER.getMessage());
    }

    private int parseToDate(String input, String errorMessage) {
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

    private Map<String, Integer> parseToOrder(String input, String errorMessage) {
        String refinedInput = refineInput(input, errorMessage);
        try {
            Matcher matcher = PATTERN.matcher(refinedInput);
            return matchAndPut(matcher);
        } catch (Exception e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private Map<String, Integer> matchAndPut(Matcher matcher) {
        Map<String, Integer> order = new HashMap<>();
        while(matcher.find()) {
            String name = matcher.group(1);
            int quantity = Integer.parseInt(matcher.group(2));
            if (order.put(name, quantity) != null) {
                throw new IllegalArgumentException();
            };
        }
        return order;
    }
}
