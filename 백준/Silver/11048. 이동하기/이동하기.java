import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    public static int[] dx = {1, 0};
    public static int[] dy = {0, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[][] map = new int[N][M];
        int[][] result = new int[N][M];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        result[0][0] = map[0][0];

        for(int x = 0; x < N; x++) {
            for(int y = 0; y < M; y++) {
                if(x-1 >= 0) {
                    result[x][y] = Math.max(result[x][y], result[x-1][y] + map[x][y]);
                }

                if(y-1>= 0) {
                    result[x][y] = Math.max(result[x][y], result[x][y-1] + map[x][y]);
                }

                if(x - 1 >= 0 && y - 1 >= 0) {
                    result[x][y] = Math.max(result[x][y], result[x-1][y-1] + map[x][y]);
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];

            for(int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(result[nx][ny] < result[x][y] + map[nx][ny]) {
                        result[nx][ny] = result[x][y] + map[nx][ny];
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        System.out.println(result[N-1][M-1]);
    }
}