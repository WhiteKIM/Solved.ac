import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int N = 0;
    public static int M = 0;
    public static int[][] graph;
    public static int[][] result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        graph = new int[N][M];
        result = new int[N][M];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(input[j]);
                result[i][j] = -1;
            }
        }

        System.out.println(solved(0, 0));
    }

    // DP + DFS
    // DP를 이용해서 이전에 최적해를 구하였다면 해당 값 사용
    // 최적해가 없는 점이라면 최적해를 구하고, 이를 저장
    // 방문 가능한 모든 점에 대해서 최적해를 구하여 최종해를 얻음
    public static int solved(int x, int y) {
        if(x == N - 1 && y == M - 1) {
            return 1;
        }

        if(result[x][y] != -1) {
            return result[x][y];
        }

        int n = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                if (graph[x][y] > graph[nx][ny]) {
                    n += solved(nx, ny);
                }
            }
        }

        result[x][y] = n;
        return result[x][y];
    }
}
