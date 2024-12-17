import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;
    public static int K;
    public static int[][] map;
    public static int count = 0;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            char[] inp = sc.nextLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = inp[j] - '0';
            }
        }

        solved();

        if(count == 0) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }

    public static void solved() {
        // x, y, 부신 벽 개수, 거리
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K+1];
        queue.add(new int[]{0, 0, 0, 1});
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int rock = arr[2];
            int dist = arr[3];

            if(x1 == N - 1 && y1 == M - 1) {
                count = dist;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(map[nx][ny] == 0) {  // 길
                        if(!visited[nx][ny][rock]) {
                            visited[nx][ny][rock] = true;
                            queue.add(new int[]{nx, ny, rock, dist + 1});
                        }
                    } else {    // 바위
                        if(rock + 1 <= K && !visited[nx][ny][rock+1]) {
                            visited[nx][ny][rock+1] = true;
                            queue.add(new int[]{nx, ny, rock+1, dist + 1});
                        }
                    }
                }
            }
        }
    }
}
