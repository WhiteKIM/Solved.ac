import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String main = sc.nextLine();
        String target = sc.nextLine();

        // 두가지 연산 정리
        // 가장 마지막에 A를 더하거나
        // 문자열을 뒤집고 B를 더함

        // Thinking
        // ABBA라면
        // 이전 문자열은 ABB => BA => B

        // 최초 문자열에서 시작해서 진행하는 방법은
        // 경우의 수가 매 연산마다 배로 늘어나므로 매우 비효율적
        // 차라리 완성된 문자열에서 시작 문자열이 가능한지 판단하자

        while (main.length() != target.length()) {
            if(target.charAt(target.length() - 1) == 'A') {
                // 해당 문자는 제거
                target = target.substring(0, target.length() - 1);
            } else {
                target = reverse(target);
                target = target.substring(1);
            }
        }

        if(main.equals(target)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static String reverse(String target) {
        StringBuilder sb = new StringBuilder();

        for(int i = target.length() - 1; i >= 0; i--) {
            sb.append(target.charAt(i));
        }

        return sb.toString();
    }
}
