import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        // 각 숫자를 하나씩 입력하면서
        // 이전에 저장한 마지막 값과 현재 값을 비교
        // 마지막값이 더 크다면 현재값을 제거
        // 현재값이 더 크다면 마지막값을 제거하고 현재값을 저장

        char[] number = sc.nextLine().toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            int num = number[i] - '0';
            while (K > 0 && stack.peekLast() != null && stack.peekLast() < num) {
                stack.pollLast();
                K-=1;
            }

            stack.addLast(num);
        }

        StringBuilder sb = new StringBuilder();

        // 제거후 남은 숫자가 모두 동일하면 K번 제거하는 조건이 맞지 않아서
        // stack.size - K > 0일 떄까지 빼서 K개를 제거하도록 맞춰줌
        while (stack.size() > K) {
            sb.append(stack.pollFirst());
        }

        System.out.println(sb.toString());
    }
}
