import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int x = 0;
    public static int y = 0;
    public static int[][] map;
    public static int[][] result;
    public static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    public static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        x = Integer.parseInt(input[0]);
        y = Integer.parseInt(input[1]);

        map = new int[x][y];
        result = new int[x][y];

        for(int i = 0; i < x; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < y; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                result[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                if(map[i][j] == 1) {
                    solved(i, j);
                }
            }
        }

        int max = 0;

        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                max = Math.max(max, result[i][j]);
            }
        }

        System.out.println(max);
    }

    public static void solved(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[x][y];
        queue.add(new int[]{i, j});
        visited[i][j] = true;//상어 위치
        result[i][j] = 0;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int k = 0; k < 8; k++) {
                int nx = x1 + dx[k];
                int ny = y1 + dy[k];

                if(0 <= nx && nx < x && 0 <= ny && ny < y) {
                    if(!visited[nx][ny] && result[x1][y1] + 1 < result[nx][ny]) {
                        visited[nx][ny] = true;
                        result[nx][ny] = result[x1][y1] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
