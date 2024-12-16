import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        char[][] map = new char[N][M];
        int x = 0;
        int y = 0;
        for(int i = 0; i < N; i++) {
            char[] chars = sc.nextLine().toCharArray();

            for(int j = 0; j < M; j++) {
                if(chars[j] == 'I') {
                    x = i;
                    y = j;
                }

                map[i][j] = chars[j];
            }
        }

        solved(map, x, y, N, M);
        if(count == 0) {
            System.out.println("TT");
        } else {
            System.out.println(count);
        }
    }

    public static void solved(char[][] map, int x, int y, int N, int M) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            if(map[x1][y1] == 'P') {
                count+=1;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && map[nx][ny] != 'X') {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
