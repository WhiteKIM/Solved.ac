import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];
        int len = 0;
        String[] input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            arr[i+1] = Integer.parseInt(input[i]);
            dp[i+1] = 1;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }

            len = Math.max(len, dp[i]);
        }

        List<Integer> res = new ArrayList<>();
        int num = len;
        for(int j = N; j > 0; j--) {
            if(dp[j] == num) {
                res.add(arr[j]);
                num--;
            }
        }

        System.out.println(len);
        Collections.reverse(res);
        for(Integer i : res) {
            System.out.print(i + " ");
        }
    }
}
