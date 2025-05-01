import java.util.*;

public class Main {
    public static final char CLEAN = '.';
    public static final char DIRTY = '*';
    public static final char FURNITURE = 'x';
    public static final char ROBOT = 'o';
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static char[][] map;
    public static int result = Integer.MAX_VALUE;
    public static int[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (true) {
            String[] input = sc.nextLine().split(" ");
            int N = Integer.parseInt(input[1]);
            int M = Integer.parseInt(input[0]);
            result = Integer.MAX_VALUE;
            Point[] dust = new Point[11];

            if(N == 0 && M == 0) {
                break;
            }

            map = new char[N][M];
            int dirtyCnt = 1;

            for(int i = 0; i < N; i++) {
                char[] inputs = sc.nextLine().toCharArray();

                for(int j = 0; j < M; j++) {
                    if(inputs[j] == ROBOT) {
                        dust[0] = new Point(i, j);
                    } else if(inputs[j] == DIRTY) {
                        dust[dirtyCnt++] = new Point(i, j);
                    }

                    map[i][j] = inputs[j];
                }
            }

            dist = new int[dirtyCnt + 1][dirtyCnt + 1];

            for(int i = 0; i < dirtyCnt; i++) {
                boolean check = false;
                Point point1 = dust[i];
                int x1 = point1.x;
                int y1 = point1.y;

                for(int j = i + 1; j < dirtyCnt; j++) {
                    Point point2 = dust[j];
                    int x2 = point2.x;
                    int y2 = point2.y;

                    int val = solved(x1, y1, x2, y2);

                    if(val == -1) {
                        result = -1;
                        check = true;
                    } else {
                        dist[i][j] = val;
                        dist[j][i] = val;
                    }
                }

                if(check) {
                    break;
                }
            }

            // 순열을 만들고, 각 조합에 대해서 계산한 후에 해당 조합들에 대해서 BFS 수행
            // 구한 결과에서 최소값을 구하여 결과 계산
            permutation(new boolean[dirtyCnt], dirtyCnt, 0, 0, 0);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());;
    }

    private static int solved(int x, int y, int x2, int y2) {
        Queue<int[]> queue = new LinkedList<>();
        int height = map.length;
        int width = map[0].length;
        boolean[][] visited = new boolean[height][width];
        // 현재위치 x, y, 이동횟수
        queue.add(new int[]{x, y, 0});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int move = arr[2];

            if(x1 == x2 && y1 == y2) {
                return move;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < height && 0 <= ny && ny < width && map[nx][ny] != FURNITURE) {
                    if(!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, move + 1});
                    }
                }
            }
        }

        return -1;
    }


    public static void permutation(boolean[] visited, int cnt, int depth, int index, int sum) {
        if(depth == cnt - 1) {
            result = Math.min(result, sum);
            return;
        }

        for(int i = 1; i < cnt; i++) {
            if(!visited[i]) {
                visited[i] = true;
                permutation(visited, cnt, depth + 1, i, sum + dist[index][i]);
                visited[i] = false;
            }
        }
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
