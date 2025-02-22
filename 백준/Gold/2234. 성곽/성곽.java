import java.util.*;

public class Main {
    public static int N = 0;
    public static int M = 0;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int groupId = 1;
    public static Block[][] map;
    public static int[][] group;
    public static boolean[][] visited;
    public static Map<Integer, Set<Integer>> graph = new HashMap<>();
    public static Map<Integer, Integer> size = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        map = new Block[N][M];
        group = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < M; j++) {
                map[i][j] = new Block(Integer.parseInt(input[j]));
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(group[i][j] == 0) {
                    grouping(i, j);
                    groupId+=1;
                }
            }
        }

        int maxRoom = 0;

        for(int i = 1; i < groupId; i++) {
            int count = 0;
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < M; y++) {
                    if(group[x][y] == i) {
                        count+=1;
                    }
                }
            }

            size.put(i, count);
            maxRoom = Math.max(maxRoom, count);
        }

        for(int i = 1; i < groupId; i++) {
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < M; y++) {
                    if(group[x][y] == i && !visited[x][y]) {
                        Set<Integer> result = find(x, y, i);
                        graph.put(i, result);
                    }
                }
            }
        }

        int max = 0;
        for(int i = 1; i < groupId; i++) {
            Set<Integer> nodes = graph.get(i);

            int room = 0;
            for(int node : nodes) {
                room = size.get(i) + size.get(node);
                max = Math.max(room, max);
            }
        }

        System.out.println(groupId - 1);
        System.out.println(maxRoom);
        System.out.println(max);
    }

    public static void grouping(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        group[x][y] = groupId;
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            Block next = map[x1][y1];

            if(!next.top) {
                // 상
                int tx = x1 + dx[0];
                int ty = y1 + dy[0];

                if(0 <= tx && tx < N && 0 <= ty && ty < M && group[tx][ty] == 0) {
                    group[tx][ty] = groupId;
                    queue.add(new int[]{tx, ty});
                }
            }

            if(!next.bottom) {
                // 하
                int bx = x1 + dx[1];
                int by = y1 + dy[1];

                if(0 <= bx && bx < N && 0 <= by && by < M && group[bx][by] == 0) {
                    group[bx][by] = groupId;
                    queue.add(new int[]{bx, by});
                }
            }

            if(!next.left) {
                // 좌
                int lx = x1 + dx[2];
                int ly = y1 + dy[2];

                if(0 <= lx && lx < N && 0 <= ly && ly < M && group[lx][ly] == 0) {
                    group[lx][ly] = groupId;
                    queue.add(new int[]{lx, ly});
                }
            }

            if(!next.right) {
                // 우
                int rx = x1 + dx[3];
                int ry = y1 + dy[3];

                if(0 <= rx && rx < N && 0 <= ry && ry < M && group[rx][ry] == 0) {
                    group[rx][ry] = groupId;
                    queue.add(new int[]{rx, ry});
                }
            }
        }
    }

    // 만날 수 있는 상대 그룹을 모두 찾자
    public static Set<Integer> find(int x, int y, int groupId) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new int[]{x, y});
        Set<Integer> result = new HashSet<>();

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                    if(group[nx][ny] != groupId) {
                        result.add(group[nx][ny]);
                    } else {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return result;
    }

    /**
     * 각 값은 벽 유무를 나타냄
     * false은 벽X, true 벽O
     */
    public static class Block {
        boolean top = false;
        boolean bottom = false;
        boolean left = false;
        boolean right = false;

        public Block(int num) {
            if(num / 8 > 0) {
                bottom = true;
                num %= 8;
            }

            if(num / 4 > 0) {
                right = true;
                num %= 4;
            }

            if(num / 2 > 0) {
                top = true;
                num %= 2;
            }

            if(num > 0) {
                left = true;
            }
        }
    }
}
