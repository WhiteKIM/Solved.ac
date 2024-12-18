import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;
    public static char[][] map;
    public static boolean[][] visited;
    public static char[] data = new char[]{'W', 'B'};
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[0]);
        int w = 0;
        int b = 0;

        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            char[] chars = sc.nextLine().toCharArray();

            for(int j = 0; j < M; j++) {
                map[i][j] = chars[j];
            }
        }

        for(char c : data) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == c && !visited[i][j]) {
                        if(c == 'W') {
                            w += solved(i, j, c);
                        } else {
                            b += solved(i, j, c);
                        }
                    }
                }
            }
        }

        System.out.println(w+" "+b);
    }

    public static int solved(int x, int y, char ch) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new int[]{x, y});
        int count = 1;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && map[nx][ny] == ch) {
                        count+=1;
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return (int) Math.pow(count, 2);
    }
}
