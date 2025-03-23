import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] dp = new int[31];

        // N = 1
        // 가능한 배치 0
        dp[1] = 0;
        // N = 2
        // 가능한 배치
        // 3
        dp[2] = 3;
        // N이 홀수라면 가능한 배치가 없는 것 같음
        // 3 * 2 블록을 구성할 수 있는 경우는 3가지
        dp[3] = 0;

        // 가능한 블록 조합
        // N = 2인 블록 3가지
        // N = 4인 블록 11가지
        // N = 6인 블록 35가지

        // N = 짝수이고, 해당 블록 형성 가능
        // 기본적으로 N = 이전값의 3배를 가지고, 다른 블록으로 조합할 수 없는 경우가 2개 추가

        for(int i = 4; i <= N; i+=2) {
            dp[i] = dp[i - 2] * 3;

            for(int j = i - 4; j >= 0; j-=2) {
                dp[i] += dp[j] * 2;
            }

            dp[i] += 2;
        }

        System.out.println(dp[N]);
    }
}
