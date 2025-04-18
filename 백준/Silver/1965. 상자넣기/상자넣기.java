import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        
        int[] arr = new int[N];
        int[] dp = new int[1001];

        String[] input = sc.nextLine().split(" ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
            dp[i] = 1;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = 0;

        for(int i = 0; i < N; i++) {
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
