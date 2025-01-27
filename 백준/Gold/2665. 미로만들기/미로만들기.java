import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[][] map;
    public static int count = Integer.MAX_VALUE;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int N;
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            String input = sc.nextLine();

            for(int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        count = solved();

        System.out.println(count);
    }

    public static int solved() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[N][N];
        queue.add(new int[]{0, 0, 0});

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];
            int cnt = arr[2];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    // 벽 안뿌심
                    if(map[nx][ny] == WHITE && visited[x][y] < visited[nx][ny]) {
                        visited[nx][ny] = visited[x][y];
                        queue.add(new int[]{nx, ny, cnt});
                    } else if(map[nx][ny] == BLACK && visited[x][y] + 1 < visited[nx][ny]) { // 벽 뿌심
                        visited[nx][ny] = visited[x][y] + 1;
                        queue.add(new int[]{nx, ny, cnt + 1});
                    }
                }
            }
        }

        return visited[N-1][N-1];
    }
}
