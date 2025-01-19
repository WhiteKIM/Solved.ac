import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        long[][] map = new long[N][N];
        long[][] dp = new long[N][N];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        dp[0][0] = 1;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i + map[i][j] < N && map[i][j] > 0) {
                    dp[(int) (i+map[i][j])][j] += dp[i][j];
                }

                if(j+map[i][j] < N && map[i][j] > 0) {
                    dp[i][(int) (j+map[i][j])] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);
    }
}