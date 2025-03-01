import java.util.*;

public class Main {
    public static int N = 0;
    public static int Q = 0;
    public static int[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int size = 0;
    public static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        Q = Integer.parseInt(input[1]);

        N = (int) Math.pow(2, N);
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        input = sc.nextLine().split(" ");
        List<Integer> fireStorm = new ArrayList<>();

        for(int i = 0; i < Q; i++) {
            fireStorm.add(Integer.parseInt(input[i]));
        }

        for(int num : fireStorm) {
            int val = (int) Math.pow(2, num);
            int[][] copy = copyArray();

            for(int i = 0; i < N; i = i + val) {
                for(int j = 0; j < N; j = j + val) {
                    rotate(i, j, val, copy);
                }
            }

            melting();
        }

        int result = countIce();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) {
                    int calcSize = calcSize(i, j);
                    size = Math.max(size, calcSize);
                }
            }
        }

        System.out.println(result);
        System.out.println(size);
    }

    // 회전 기준 범위 내에서 시계순으로 회전
    private static void rotate(int x, int y, int val, int[][] copy) {
        for(int i = 0; i < val; i++) {
            for(int j = 0; j < val; j++) {
                map[x + j][y + val - i - 1] = copy[x + i][y + j];
            }
        }
    }

    public static int[][] copyArray() {
        int[][] copy = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
            }
        }

        return copy;
    }

    public static int countIce() {
        int result = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) {
                    result += map[i][j];
                }
            }
        }

        return result;
    }

    public static int calcSize(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        int count = 1;
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny] && map[nx][ny] > 0) {
                        visited[nx][ny] = true;
                        count+=1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return count;
    }

    public static void melting() {
        List<int[]> meltingList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int count = 0;

                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                        if(map[nx][ny] > 0) {
                            count+=1;
                        }
                    }
                }

                if(count < 3) {
                    meltingList.add(new int[]{i, j});
                }
            }
        }

        for(int[] arr : meltingList) {
            int x = arr[0];
            int y = arr[1];

            map[x][y] -= 1;
        }
    }
}
