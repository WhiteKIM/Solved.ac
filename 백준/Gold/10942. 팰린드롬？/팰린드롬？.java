import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(sc.readLine());
        int[] arr = new int[N+1];
        int[][] dp = new int[N+1][N+1];
        String[] input = sc.readLine().split(" ");

        for(int i = 0; i < N; i++) {
            arr[i+1] = Integer.parseInt(input[i]);
        }
        
        // 자기자신만은 팰린드롬
        for(int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        // 2개의 숫자일 때 두 값이 동일해야 팰린드롬
        for(int i = 1; i < N; i++) {
            if(arr[i] == arr[i+1])
                dp[i][i+1] = 1;
            else
                dp[i][i+1] = 0;
        }

        // 3개 이상 팬린드롬 판별
        // 중간에 낀 수가 팰린드롬이고, 양 끝의 수가 같다면 팰린드롬이지 않은가?
        for(int i = N - 1; i >= 1; i--) {
            for(int j = i + 2; j <= N; j++) {
                if(arr[i] == arr[j] && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                }
            }
        }

        // 결과출력
        int M = Integer.parseInt(sc.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            input = sc.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            sb.append(dp[x][y]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
