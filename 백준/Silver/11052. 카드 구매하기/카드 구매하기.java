import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] card = new int[N+1];
        int[] dp = new int[N+1];

        String[] input = sc.nextLine().split(" ");
        for(int i = 0; i < N; i++) {
            card[i+1] = Integer.parseInt(input[i]);
        }

        dp[1] = card[1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i - j] + card[j], dp[i]);
            }
        }

        int max = 0;

        for(int i = 1; i<= N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
