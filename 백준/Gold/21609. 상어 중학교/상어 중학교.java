import java.util.*;

public class Main {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int N = 0;
    public static int M = 0;
    public static int POINT = 0;
    public static final int BLACK = -1;
    public static final int RAINBOW = 0;
    public static final int EMPTY = -2;
    public static int[][] map;
    // 가장 큰 그룹 정보를 기록한다.
    public static Set<int[]> groups = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        while (true) {
            groups = new HashSet<>();

            for(int color = 1; color <= M; color++) {
                boolean[][] visited = new boolean[N][N];
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        if(map[i][j] == color && !visited[i][j]) {
                            play(i, j, color, visited);
                        }
                    }
                }
            }
            // 가장 큰 그룹으로 점수를 체크
            calcPoint();

            // 중력 작용
            gravity();

            // 반시계 회전
            rotate();

            // 다시 중력
            gravity();

            if(groups.isEmpty()) {
                break;
            }
        }
        
        System.out.println(POINT);
    }

    // 메인 로직
    public static void play(int i, int j, int color, boolean[][] visited) {
        Set<int[]> groups = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        groups.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];

            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny] && (map[nx][ny] ==  color || map[nx][ny] == RAINBOW)) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                        groups.add(new int[]{nx, ny});
                    }
                }
            }
        }
        
        // 하나로 이루어진 그룹은 필요없음
        if(groups.size() < 2)
            return;

        compareGroups(groups);
    }

    // 그룹 비교
    public static void compareGroups(Set<int[]> compGroup) {
        int[] mainBaseBlock = {N -1, N - 1};
        int[] compBaseBlock = {N -1, N - 1};

        // 크기 비교
        if(groups.size() < compGroup.size()) {
            groups = compGroup;
            return;
        } else if(groups.size() == compGroup.size()) {
            // 무지개 블록 개수
            int mainRain = 0;
            int compRain = 0;

            // 무지개블록을 찾으면서 겸사겸사 기준 블록을 검색
            for(int[] loc : groups) {
                int x = loc[0];
                int y = loc[1];

                if(map[x][y] == RAINBOW) {
                    mainRain+=1;
                } else if (map[x][y] != BLACK) {
                    if(mainBaseBlock[0] > x) {
                        mainBaseBlock = new int[]{x, y};
                    } else if(mainBaseBlock[0] == x) {
                        if(mainBaseBlock[1] > y) {
                            mainBaseBlock = new int[]{x, y};
                        }
                    }
                }
            }

            for(int[] loc : compGroup) {
                int x = loc[0];
                int y = loc[1];

                if(map[x][y] == RAINBOW) {
                    compRain+=1;
                } else if (map[x][y] != BLACK) {
                    if(compBaseBlock[0] > x) {
                        compBaseBlock = new int[]{x, y};
                    } else if(compBaseBlock[0] == x) {
                        if(compBaseBlock[1] > y) {
                            compBaseBlock = new int[]{x, y};
                        }
                    }
                }
            }

            if(mainRain < compRain) {
                groups = compGroup;
                return;
            } else if(mainRain == compRain) {
                // 기준 블록 행
                int mainMaxRow = mainBaseBlock[0];
                int compMaxRow = compBaseBlock[0];

                if(mainMaxRow < compMaxRow) {
                    groups = compGroup;
                    return;
                } else if(mainMaxRow == compMaxRow) {
                    // 기준 블록 열
                    int mainMaxCol = mainBaseBlock[1];
                    int compMaxCol = compBaseBlock[1];

                    if(mainMaxCol < compMaxCol) {
                        groups = compGroup;
                        return;
                    }
                }
            }
        }
    }

    // 중력
    public static void gravity() {
        // 밑에서부터 중력이 적용될 대상과 위치를 검색
        int[][] copy = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(copy[i], EMPTY);
        }

        for(int y = 0; y < N; y++) {
            int lastGround = N - 1;

            // 바닥에 위치한 값은
            for(int x = N - 1; x >= 0; x--) {
                int block = map[x][y];

                if(block == BLACK) {
                    copy[x][y] = BLACK;
                    lastGround = x - 1;
                } else if(block != EMPTY) {
                    copy[lastGround][y] = block;
                    lastGround-=1;
                }
            }
        }

        map = copy;
    }

    // 회전 담당
    // 반시계로 회전시킨다.
    public static void rotate() {
        int[][] copy = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                copy[N - j -1][i] = map[i][j];
            }
        }

        map = copy;
    }

    public static void calcPoint() {
        int size = groups.size();

        POINT += (int) Math.pow(size, 2);

        // 그룹 내 블록 제거
        for(int[] loc : groups) {
            int x = loc[0];
            int y = loc[1];

            map[x][y] = EMPTY;
        }
    }
}
