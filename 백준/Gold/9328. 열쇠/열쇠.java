import java.util.*;

public class Main {
    public static Set<Character> keys = new HashSet<>();
    public static List<int[]> enteredList = new ArrayList<>();
    public static int T = 0;
    public static int N = 0;
    public static int M = 0;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static Map<Set<Character>, boolean[][]> visited = new HashMap<>();
    public static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < T; i++) {
            String[] input = sc.nextLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            map = new char[N+2][M+2];

            for(int j = 0; j < M + 2; j++) {
                map[0][j] = '.';
                map[N+1][j] = '.';
            }

            for(int j = 1; j < N + 1; j++) {
                map[j][0] = '.';
                char[] chars = sc.nextLine().toCharArray();

                for(int k = 1; k <= M; k++) {
                    map[j][k] = chars[k-1];
                }

                map[j][M+1] = '.';
            }


            String str = sc.nextLine();

            // 먼저 가지고 있는 열쇠
            if(str.charAt(0) != '0') {
                char[] key = str.toCharArray();

                for(char c : key) {
                    keys.add(c);
                }

                visited.put(keys, new boolean[N+2][M+2]);
            }

            // 진입가능한 지점 찾기
            for(int j = 0; j < N + 2; j++) {
                enteredList.add(new int[]{j, 0});
                enteredList.add(new int[]{j, M+1});
            }

            for(int j = 0; j < M + 2; j++) {
                enteredList.add(new int[]{0, j});
                enteredList.add(new int[]{N+1, j});
            }

            int num = solved();
            sb.append(num).append("\n");

            // 값 초기화
            visited = new HashMap<>();
            enteredList = new ArrayList<>();
            keys = new HashSet<>();
        }

        System.out.println(sb.toString());
    }

    private static int solved() {
        int count = 0;
        Queue<Move> queue = new LinkedList<>();
        if(!visited.containsKey(keys))
            visited.put(keys, new boolean[N+2][M+2]);

        for(int[] arr : enteredList) {
            queue.add(new Move(arr[0], arr[1], keys));
            visited.get(keys)[arr[0]][arr[1]] = true;
        }

        while (!queue.isEmpty()) {
            Move m = queue.poll();
            int x = m.x;
            int y = m.y;
            Set<Character> tempKey = m.key;

            if(map[x][y] == '$') {
                // 문서를 찾았다.
                count+=1;
                map[x][y] = '.';//같은 문서를 두 번 찾기말기
            } else if('a' <= map[x][y] && map[x][y] <= 'z') {
                tempKey.add(map[x][y]);    // 열쇠를 찾았다.
                if(!visited.containsKey(tempKey)) {
                    visited.put(tempKey, new boolean[N+2][M+2]);
                }
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 <= nx && nx < N+2 && 0 <= ny && ny < M + 2) {
                    if(!visited.get(tempKey)[nx][ny]) {
                        if('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {
                            char door = Character.toLowerCase(map[nx][ny]);

                            if(tempKey.contains(door)) {
                                // 열쇠가 있다면 진입
                                visited.get(tempKey)[nx][ny] = true;
                                queue.add(new Move(nx, ny, tempKey));
                            }
                        } else if(map[nx][ny] != '*') {
                            // 벽이 아니라면 진입
                            visited.get(tempKey)[nx][ny] = true;
                            queue.add(new Move(nx, ny, tempKey));
                        }
                    }
                }
            }
        }

        return count;
    }

    public static class Move {
        int x;
        int y;
        Set<Character> key;

        public Move(int x, int y, Set<Character> key) {
            this.x = x;
            this.y = y;
            this.key = key;
        }
    }
}
