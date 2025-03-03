import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] wine = new int[N+1];
        int[] dp = new int[N+1];

        for(int i = 0; i < N; i++) {
            wine[i+1] = Integer.parseInt(sc.nextLine());
        }

        dp[1] = wine[1];
        if(N >= 2) {
            dp[2] = wine[1] + wine[2];
        }

        for(int i = 3; i <= N; i++) {
            // 선택가능한 경우는 3가지
            // 전전 결과에서 현재 와인값 선택
            // 전전전 결과에서 연속으로 전와인과 현와인 선택
            // 일단 안마시고 건너뛴다.
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
        }

        System.out.println(dp[N]);
    }
}
