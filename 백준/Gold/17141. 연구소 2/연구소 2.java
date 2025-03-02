import java.util.*;

public class Main {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int N = 0;
    public static int M = 0;
    public static final int INF = 9999999;
    public static int MAX = Integer.MAX_VALUE;
    public static List<int[]> virusList = new ArrayList<>();
    public static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int cnt = 0;

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                int n = Integer.parseInt(input[j]);

                if(n == 2) {
                    virusList.add(new int[]{i, j});
                    map[i][j] = 0;
                    cnt+=1;
                } else {
                    map[i][j] = n;
                }
            }
        }

        combination(new boolean[cnt], 0, cnt, M);

        if(MAX == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(MAX);
        }
    }

    public static void combination(boolean[] visited, int depth, int r, int c) {
        if(c == 0) {
            int[][] data = makeVisitedArray();
            Queue<int[]> queue = new LinkedList<>();

            for(int i = 0; i < r; i++) {
                if(visited[i]) {
                    int[] arr = virusList.get(i);
                    int x = arr[0];
                    int y = arr[1];

                    queue.add(new int[]{x, y});
                    data[x][y] = 0;
                }
            }

            int solved = solved(data, queue);

            if(solved != -1) {
                MAX = Math.min(solved, MAX);
            }
        }

        for(int i = depth; i < r; i++) {
            visited[i] = true;
            combination(visited, i+1, r, c - 1);
            visited[i] = false;
        }
    }

    public static int[][] makeVisitedArray() {
        int[][] visited = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visited[i][j] = INF;
            }
        }

        return visited;
    }

    private static int solved(int[][] visited, Queue<int[]> queue) {
        int time = 0;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N && visited[x1][y1] + 1 < visited[nx][ny]) {
                    if(map[nx][ny] != 1) {
                        visited[nx][ny] = visited[x1][y1] + 1;
                        time = Math.max(time, visited[nx][ny]);
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        if(isComplete(visited)) {
            return time;
        } else {
            return -1;
        }
    }

    public static boolean isComplete(int[][] visited) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0 && visited[i][j] == INF) {
                    return false;
                }
            }
        }

        return true;
    }
}
