import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int K;
    public static int[][] map;
    public static int[][][] time;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        map = new int[N][N];
        time = new int[K+1][N][N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int w = 1; w <= K; w++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    time[w][i][j] = Integer.MAX_VALUE;
                }
            }
        }

        Queue<int[]> node = new LinkedList<>();
        for(int w = 1; w <= K; w++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == w) {
                        node.add(new int[]{i, j, w, 0});
                        time[w][i][j] = 0;
                    }
                }
            }
        }

        solved(node);

        input = sc.nextLine().split(" ");
        int s = Integer.parseInt(input[0]);
        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);

        System.out.println(findValue(x - 1, y - 1, s));
    }

    public static void solved(Queue<int[]> queue) {
        boolean[][] visited = new boolean[N][N];

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int k = arr[2];
            int cnt = arr[3];

            visited[x1][y1] = true;

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny] && cnt + 1 < time[k][nx][ny]) {
                        visited[nx][ny] = true;
                        time[k][nx][ny] = cnt+1;
                        queue.add(new int[]{nx, ny, k, cnt + 1});
                    }
                }
            }
        }
    }

    public static int findValue(int x, int y, int s) {
        int index = 0;
        int min = Integer.MAX_VALUE;

        for(int i = 1; i <= K; i++) {
            if(min > time[i][x][y]) {
                min = time[i][x][y];
                index = i;
            }
        }

        if(min > s) {
            return 0;
        }

        return index;
    }
}