import java.util.*;

public class Main {
    public static int N = 0;
    public static int M = 0;
    public static char[][] map;
    public static int count = Integer.MAX_VALUE;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];

        int x = -1;
        int y = -1;

        for(int i = 0; i < N; i++) {
            char[] chars = sc.nextLine().toCharArray();

            for(int j = 0; j < M; j++) {
                char c = chars[j];
                map[i][j] = c;

                if(c == '0') {
                    x = i;
                    y = j;
                }
            }
        }

        solved(x, y);

        if(count == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }

    public static void solved(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        Map<Set<Character>, boolean[][]> visited = new HashMap<>();
        HashSet<Character> keySet = new HashSet<>();
        queue.add(new Point(x, y, 0, keySet));

        if(!visited.containsKey(keySet)) {
            visited.put(keySet, new boolean[N][M]);
        }

        visited.get(keySet)[x][y] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x1 = point.x;
            int y1 = point.y;
            Set<Character> keys = point.key;
            int cnt = point.count;

            if(map[x1][y1] == '1') {
                count = Math.min(cnt, count);
                continue;
            }

            Set<Character> newKeys = new HashSet<>(keys);
            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited.get(keys)[nx][ny]) {
                        if(isKey(map[nx][ny])) {
                            newKeys.add(map[nx][ny]);

                            if(visited.get(newKeys) == null) {
                                visited.put(newKeys, new boolean[N][M]);
                            }

                            if(!visited.get(newKeys)[nx][ny]) {
                                visited.get(newKeys)[nx][ny] = true;
                                queue.add(new Point(nx, ny, cnt+1, newKeys));
                            }
                        } else if(isDoor(map[nx][ny]) && canIOpenDoor(map[nx][ny], keys)) {
                            visited.get(keys)[nx][ny]= true;
                            queue.add(new Point(nx, ny, cnt + 1, keys));
                        } else if(map[nx][ny] == '.' || map[nx][ny] == '1' || map[nx][ny] == '0') {
                            visited.get(keys)[nx][ny] = true;
                            queue.add(new Point(nx, ny, cnt + 1, keys));
                        }
                    }
                }
            }
        }
    }

    public static boolean isKey(char ch) {
        return ch == 'a' || ch == 'b' || ch == 'c' || ch == 'd' || ch == 'e' || ch == 'f';
    }

    public static boolean isDoor(char ch) {
        return ch == 'A' || ch == 'B' || ch == 'C' || ch == 'D' || ch == 'E' || ch == 'F';
    }

    public static boolean canIOpenDoor(char ch, Set<Character> key) {
        switch (ch) {
            case 'A':
                return key.contains('a');
            case 'B':
                return key.contains('b');
            case 'C':
                return key.contains('c');
            case 'D':
                return key.contains('d');
            case 'E':
                return key.contains('e');
            case 'F':
                return key.contains('f');
            default:
                return false;
        }
    }

    public static class Point {
        int x;
        int y;
        int count;
        Set<Character> key = new HashSet<>();

        public Point(int x, int y, int count, Set<Character> key) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.key = key;
        }
    }
}
