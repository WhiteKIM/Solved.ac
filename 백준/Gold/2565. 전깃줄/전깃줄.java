import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] arr = new int[501];
        int[] dp = new int[501];

        // 8 2 9 1 0 4 6 0 7 10
        // 0은 전기줄을 연결하지 않는 경우
        //
        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            arr[x] = y;
        }

        for(int i = 1; i <= 500; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;

        for(int i = 1; i <= 500; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
    }
}
