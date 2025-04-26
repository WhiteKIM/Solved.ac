import java.util.*;

public class Main {
    public static final char WALL = '#';
    public static final char COIN = 'o';
    public static final char BLOCK = '.';
    public static char[][] map;
    public static int N;
    public static int M;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];
        List<int[]> coinLoc = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            char[] inputs = sc.nextLine().toCharArray();

            for(int j = 0; j < M; j++) {
                if(inputs[j] == COIN)
                    coinLoc.add(new int[]{i, j});

                map[i][j] = inputs[j];
            }
        }

        result = solved(coinLoc.get(0)[0], coinLoc.get(0)[1], coinLoc.get(1)[0],coinLoc.get(1)[1]);
        System.out.println(result);
    }

    private static int solved(int c1_x, int c1_y, int c2_x, int c2_y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{c1_x, c1_y, c2_x, c2_y, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int x2 = arr[2];
            int y2 = arr[3];
            int cnt = arr[4];

            if(cnt >= 10)
                return -1;

            for(int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];

                // 두 동전이 모두 장외로 빠지는 경우
                if((nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) && (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M)) {
                    continue;
                } else if((nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) || (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M)) {
                    // 둘 중 하나만 장외로 빠지는 경우
                    return cnt + 1;
                } else {
                    // 두 코인 모두 지도 내에 위치
                    if (map[nx1][ny1] == WALL) {
                        nx1 = x1;
                        ny1 = y1;
                    }

                    if(map[nx2][ny2] == WALL) {
                        nx2 = x2;
                        ny2 = y2;
                    }

                    queue.add(new int[]{nx1, ny1, nx2, ny2, cnt + 1});
                }
            }
        }

        return -1;
    }
}
