import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<App> appList = new ArrayList<>();

        String[] mem = sc.nextLine().split(" ");
        String[] price = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            int memory = Integer.parseInt(mem[i]);
            int pr = Integer.parseInt(price[i]);
            appList.add(new App(memory, pr));
        }

        int[][] dp = new int[N][10001];
        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            App app = appList.get(i);

            for(int j = 0; j <= 10000; j++) {
                if(i == 0 && j >= app.price) {
                    dp[i][j] = app.memory;
                }

                if(i >= 1) {
                    // i개의 프로그램을 종료하고, j의 실행값을 가질 때 메모리 총량
                    if(j - app.price >= 0) {
                        dp[i][j] = Math.max(dp[i-1][j - app.price] + app.memory, dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }

                // 실행값 찾기
                if(dp[i][j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
        }

        System.out.println(answer);
    }

    public static class App{
        int memory;
        int price;

        public App(int memory, int price) {
            this.memory = memory;
            this.price = price;
        }
    }
}
