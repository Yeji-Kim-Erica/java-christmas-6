package christmas.view;

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
}
