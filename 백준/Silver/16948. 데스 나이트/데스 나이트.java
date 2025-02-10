import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N = 0;
    public static int[][] map;
    public static int[] dx = {-2, -2, 0, 0, 2, 2};
    public static int[] dy = {-1, 1, -2, 2, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().split(" ");
        int r1 = Integer.parseInt(input[0]);
        int c1 = Integer.parseInt(input[1]);
        int r2 = Integer.parseInt(input[2]);
        int c2 = Integer.parseInt(input[3]);

        map = new int[N][N];

        System.out.println(solved(r1, c1, r2, c2));
    }

    public static int solved(int x1, int y1, int x2, int y2) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[]{x1, y1, 0});
        visited[x1][y1] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];
            int cnt = arr[2];

            if(x == x2 && y == y2) {
                return cnt;
            }

            for(int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, cnt+1});
                    }
                }
            }
        }

        return -1;
    }
}
