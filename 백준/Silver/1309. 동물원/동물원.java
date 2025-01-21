import java.util.Scanner;

public class Main {
    public static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 3;

        for(int i = 2; i <= N; i++) {
            // i번쨰 조합은 이전 조합을 이용해서 구성할 수 있다.
            dp[i] = (dp[i-1] * 2 + dp[i-2]) % 9901;
        }

        System.out.println(dp[N]);
    }
}
