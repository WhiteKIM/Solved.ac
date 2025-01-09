import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[][] dp = new int[1001][1001];

        dp[0][0] = 1;

        for(int i = 0; i <= 1000; i++) {
            for(int j = 0; j <= Math.min(i, K); j++) {
                if(j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j])%10007;
            }
        }

        System.out.println(dp[N][K] % 10007);
    }
}
