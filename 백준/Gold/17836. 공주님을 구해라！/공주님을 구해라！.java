import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;
    public static int T;
    public static int[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static final int INF = 999999999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int result = solved();

        if(result == INF || result > T) {
            System.out.println("Fail");
        } else {
            System.out.println(result);
        }
    }

    private static int solved() {
        Queue<int[]> queue = new LinkedList<>();
        int[][][] visited = new int[2][N][M];
        queue.add(new int[]{0, 0, 0, 0});

        for(int k = 0; k < 2; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    visited[k][i][j] = INF;
                }
            }
        }

        visited[0][0][0] = 0;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];
            int time = arr[2];
            int state = arr[3];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(map[nx][ny] == 0 && visited[state][x][y] + 1 < visited[state][nx][ny]) {
                        visited[state][nx][ny] = visited[state][x][y] + 1;
                        queue.add(new int[]{nx, ny, time + 1, state});
                    } else if(map[nx][ny] == 2 && visited[state][x][y] + 1 < visited[1][nx][ny]) {
                        visited[1][nx][ny] = visited[state][x][y] + 1;
                        queue.add(new int[]{nx, ny, time + 1, 1});
                    } else {
                        // 벽
                        if(state == 1 && visited[state][x][y] + 1 < visited[state][nx][ny]) {
                            // 벽 부시고 지나감
                            visited[state][nx][ny] = visited[state][x][y] + 1;
                            queue.add(new int[]{nx, ny, time + 1, 1});
                        }
                    }
                }
            }
        }

        return Math.min(visited[0][N-1][M-1], visited[1][N-1][M-1]);
    }
}
