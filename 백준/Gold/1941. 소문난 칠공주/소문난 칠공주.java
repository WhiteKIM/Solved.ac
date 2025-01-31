import java.util.*;

public class Main {
    public static int result = 0;
    public static char[][] map = new char[5][5];
    public static int[] arr = new int[25];
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 5; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        for(int i = 0; i < 25; i++) {
            arr[i] = i;
        }

        comb(new boolean[25], 0, 7, 0);

        System.out.println(result);
    }

    public static boolean solved(boolean[] selection) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        int index = 0;

        for(int i =0; i < 25; i++) {
            if(selection[i]) {
                index = i;
                break;
            }
        }

        int x = index / 5;
        int y = index % 5;
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                index = nx * 5 + ny;

                if(0 <= nx && nx < 5 && 0 <= ny && ny < 5 && selection[index] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    count+=1;
                }
            }
        }

        return count == 7;
    }


    // 일단 4개의 점을 선택하고, 해당 점이 모두 다솜파인 경우에만 BFS를 사용해서 인접한 범위인지 판단한다.
    public static void comb(boolean[] select, int start, int r, int s) {
        if(r == 0) {
            if(s >= 4 && solved(select)) {
                result+=1;
            }

            return;
        }

        for(int i = start; i <= 24; i++) {
            select[i] = true;
            int x = i / 5;
            int y = i % 5;
            comb(select, i + 1, r - 1, s + (map[x][y]== 'S' ? 1 : 0));
            select[i] = false;
        }
    }
}
