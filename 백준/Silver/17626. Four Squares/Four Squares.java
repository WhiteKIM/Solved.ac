import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] dp = new int[50001];


        // 일단 임의의 큰 수로 초기화
        for(int i = 1; i <= 50000; i++) {
            dp[i] = 999999;
        }

        // 이미 제곱수인 경우를 구해서 초기화
        for(int i = 1; i * i <= 50000; i++) {
            dp[i * i] = 1;
        }

        for(int i = 2; i <= 50000; i++) {
            for(int j = 1; j * j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j * j] + dp[(i - j * j)]);
            }
        }

        System.out.println(dp[N]);
    }
}
