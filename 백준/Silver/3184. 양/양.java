import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;
    public static int wolves = 0;
    public static int sheeps = 0;
    public static char[][] farm;
    public static boolean[][] visited;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        farm = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            farm[i] = sc.nextLine().toCharArray();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(farm[i][j] != '#' && !visited[i][j]) {
                    solved(i, j);
                }
            }
        }

        System.out.println(sheeps+" "+wolves);
    }

    public static void solved(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        int wolf = 0;
        int sheep = 0;
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            if(farm[x1][y1] == 'v') {
                wolf+=1;
            } else if(farm[x1][y1] == 'o') {
                sheep+=1;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && farm[nx][ny] != '#') {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        if(wolf < sheep) {
            sheeps += sheep;
        } else {
            wolves += wolf;
        }
    }
}
