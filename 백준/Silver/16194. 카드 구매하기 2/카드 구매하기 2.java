import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().split(" ");

        int[] packPrice = new int[input.length + 1];

        for(int i = 0; i < input.length; i++) {
            packPrice[i + 1] = Integer.parseInt(input[i]);
        }

        int[] dp = new int[N+1];
        dp[0] = 0;

        // 카드구매최소 비용 : 현재 가진 카드 비용 + 새로 구매할 카드비용(i - 카드비용)
        for(int i = 1; i <= N; i++) {
            dp[i] = packPrice[i];

            for(int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + packPrice[j]);
            }
        }

        System.out.println(dp[N]);
    }
}
