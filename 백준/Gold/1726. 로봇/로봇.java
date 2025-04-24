import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N = 0;
    public static int M = 0;
    public static int[][] map;
    public static int[][][] visited;
    public static final int START = 3;
    public static final int END = 4;
    public static int[][] turn = {
            {3, 2}, // 현재 : 동
            {2, 3}, // 현재 : 서
            {0, 1}, // 현재 : 남
            {1, 0}  // 현재 : 북
    };
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        visited = new int[4][N][M];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < M; j++) {
                int num = Integer.parseInt(input[j]);
                map[i][j] = num;
            }
        }

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    visited[i][j][k] = 999999999;
                }
            }
        }

        input = sc.nextLine().split(" ");
        int start_x = Integer.parseInt(input[0]) - 1;
        int start_y = Integer.parseInt(input[1]) - 1;
        int start_dir = Integer.parseInt(input[2]) - 1;
        map[start_x][start_y] = START;

        input = sc.nextLine().split(" ");
        int end_x = Integer.parseInt(input[0]) - 1;
        int end_y = Integer.parseInt(input[1]) - 1;
        int end_dir = Integer.parseInt(input[2]) - 1;
        map[end_x][end_y] = END;

        solved(start_x, start_y, start_dir);
        System.out.println(visited[end_dir][end_x][end_y]);
    }

    public static void solved(int x, int y, int dir) {
        Queue<int[]> queue = new LinkedList<>();
        // x, y, dir
        queue.add(new int[]{x, y, dir});
        visited[dir][x][y] = 0;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int dir1 = arr[2];
            int cnt = visited[dir1][x1][y1];

            // 직진만
            for(int k = 1; k < 4; k++) {
                int nx = x1 + dx[dir1] * k;
                int ny = y1 + dy[dir1] * k;

                if(nx < 0 || nx >= N || ny < 0 || ny >= M)
                    break;

                if(map[nx][ny] == 1)
                    break;

                if(visited[dir1][nx][ny] > cnt + 1) {
                    visited[dir1][nx][ny] = cnt + 1;
                    queue.add(new int[]{nx, ny, dir1});
                }
            }

            // 회전만
            int left = turn[dir1][0];
            int right = turn[dir1][1];


            if(visited[left][x1][y1] > cnt + 1) {
                visited[left][x1][y1] = cnt + 1;
                queue.add(new int[]{x1, y1, left});
            }

            if(visited[right][x1][y1] > cnt + 1) {
                visited[right][x1][y1] = cnt + 1;
                queue.add(new int[]{x1, y1, right});
            }
        }
    }
}