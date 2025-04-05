import java.util.Scanner;

public class Main {
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());

        for(int c = 0; c < T; c++) {
            int N = Integer.parseInt(sc.nextLine());
            int[] arr = new int[N+1];
            int[] sum = new int[N+1];
            int[][] dp = new int[N+1][N+1];
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                arr[j+1] = Integer.parseInt(input[j]);
            }
            
            // 파일을 합칠때 발생하는 비용 계산
            for(int i = 1; i <= N; i++) {
                sum[i] = sum[i - 1] + arr[i];
            }

            // 기본 초기화
            for(int i = 1; i <= N; i++) {
                for(int j = i; j <= N; j++) {
                    if(i == j) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = INF;
                    }
                }
            }

            for(int i = 2; i <= N; i++) {
                for(int j = 1; j <= N - i + 1; j++) {
                    int num = i + j - 1;
                    for(int k = j; k < num; k++) {
                        // 두 부분최적해를 합치면서 발생한 비용을 재계산
                        int cost = dp[j][k] + dp[k+1][num] + sum[num] - sum[j - 1];
                        dp[j][num] = Math.min(dp[j][num], cost);
                    }
                }
            }

            sb.append(dp[1][N]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
