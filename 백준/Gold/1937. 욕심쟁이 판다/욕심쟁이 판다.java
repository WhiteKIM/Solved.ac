import java.util.Scanner;

public class Main {
    public static int N = 0;
    public static int[][] map;
    public static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        int result = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                solved(i, j, 1);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println(result + 1);
    }

    public static int solved(int x, int y, int cnt) {
        if(dp[x][y] != 0) {
            return dp[x][y];
        } 

        // 4방향 탐색
        if(x + 1 < N && map[x][y] < map[x + 1][y]) {
            solved(x+1, y, cnt + 1);
            dp[x][y] = Math.max(dp[x][y], dp[x+1][y] + 1);
        }

        if(x - 1 >= 0 && map[x][y] < map[x - 1][y]) {
            solved(x-1, y, cnt + 1);
            dp[x][y] = Math.max(dp[x][y], dp[x - 1][y] + 1);
        }

        if(y + 1 < N && map[x][y] < map[x][y + 1]) {
            solved(x, y + 1, cnt + 1);
            dp[x][y] = Math.max(dp[x][y], dp[x][y + 1] + 1);
        }

        if(y - 1 >= 0 && map[x][y] < map[x][y - 1]) {
            solved(x, y - 1, cnt + 1);
            dp[x][y] = Math.max(dp[x][y], dp[x][y - 1] + 1);
        }

        return 1;
    }
}
