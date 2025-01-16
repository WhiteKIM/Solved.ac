import java.util.Scanner;

public class Main {
    public static int divide = 1000000009;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        long[] dp = new long[1000001];
        dp[1] = 1;// 1
        dp[2] = 2;// 1 + 1, 2
        dp[3] = 4;// 1 + 1 + 1, 1 + 2, 2 + 1, 3

        for(int i = 4; i <= 1000000; i++) {
            dp[i] = (dp[i - 1]) + (dp[i - 2]) + (dp[i - 3]);
            dp[i] %= divide;
        }

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(sc.nextLine());
            System.out.println(dp[N] % divide);
        }
    }
}
