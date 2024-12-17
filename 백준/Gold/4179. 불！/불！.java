import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;
    public static char[][] map;
    public static int[][] burn;
    public static int person = Integer.MAX_VALUE;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int desX = -1;
    public static int desY = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        burn = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            char[] chars = sc.nextLine().toCharArray();

            for(int j = 0; j < M; j++) {
                map[i][j] = chars[j];
                burn[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'F') {
                    burning(i, j);
                }
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 'J') {
                    person = escape(i, j);
                }
            }
        }
        
        if(person == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(person);
        }
    }

    public static void burning(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        queue.add(new int[]{x, y, 1});
        burn[x][y] = 1;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int cnt = arr[2];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && (map[nx][ny] != '#' && map[nx][ny] != 'F')) {
                        burn[nx][ny] = Math.min(burn[nx][ny], cnt+1);
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, cnt+1});
                    }
                }
            }
        }
    }

    public static int escape(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        queue.add(new int[]{x, y, 1});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int cnt = arr[2];

            if(x1 == 0 || x1 == N - 1 || y1 == 0 || y1 == M -1) {
                if(burn[x1][y1] < cnt) {
                    return Integer.MAX_VALUE;
                }

                return cnt;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && map[nx][ny] == '.' && burn[nx][ny] > cnt + 1) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, cnt+1});
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
