import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] map;
    public static Point[][] zeroMap;
    public static int[][] result;
    public static boolean[][] visited;
    public static int N = 0;
    public static int M = 0;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        List<int[]> zero = new ArrayList<>();
        List<int[]> one = new ArrayList<>();

        map = new int[N][M];
        zeroMap = new Point[N][M];
        result = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();

            for(int j = 0; j < M; j++) {
                int val = str.charAt(j) - '0';
                map[i][j] = val;
                if(val == 0) {
                    zero.add(new int[]{i, j});
                } else {
                    one.add(new int[]{i, j});
                }

                zeroMap[i][j] = new Point(0, 0);
            }
        }

        int grpId = 1;

        for(int[] arr : zero) {
            solved(arr[0], arr[1], grpId);
            count = 0;
            grpId+=1;
        }

        for(int[] arr : one) {
            find(arr[0], arr[1]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    sb.append((result[i][j] + 1) % 10);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void find(int x, int y) {
        Set<Integer> grpSet = new HashSet<>();
        // 사방향 체크
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                if(!grpSet.contains(zeroMap[nx][ny].groupId)) {
                    result[x][y] += zeroMap[nx][ny].count;
                    grpSet.add(zeroMap[nx][ny].groupId);
                }

            }
        }
    }

    public static void solved(int x, int y, int grpId) {
        if(visited[x][y])
            return;

        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> list = new ArrayList<>();
        queue.add(new int[]{x, y});
        count+=1;
        visited[x][y] = true;
        list.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && map[nx][ny] == 0) {
                        count+=1;
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                        list.add(new int[]{nx, ny});
                    }
                }
            }
        }

        for(int[] arr : list) {
            zeroMap[arr[0]][arr[1]].count = count;
            zeroMap[arr[0]][arr[1]].groupId = grpId;
        }
    }

    public static class Point {
        int count;
        int groupId;

        public Point(int cont, int groupId) {
            this.count = count;
            this.groupId = groupId;
        }
    }
}
