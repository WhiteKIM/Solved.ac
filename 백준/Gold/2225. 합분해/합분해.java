import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[][] dp = new int[K+1][N+1];

        for(int i = 0; i <= N; i++) {
            dp[1][i] = 1;
        }

        for(int i = 1; i <= K; i++) {
            dp[i][0] = 1;
        }

        // K = 1
        // 1 1 1 1 1
        // K = 2
        // 2 3 4 5 6
        // K = 3
        // 3 6 10
        // K = M이고, N의 값은
        // dp[K][N] = dp[K-1][N] + dp[K][N-1];

        for(int i = 2; i <= K; i++) {
            for(int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
            }
        }

        System.out.println(dp[K][N] % MOD);
    }
}
