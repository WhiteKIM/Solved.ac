import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int[] dx = {-1, 0, 1};
    public static int[] dy = {1, 1, 1};
    public static int N = 0;
    public static int M = 0;
    public static char[][] map;
    public static boolean[][] visited;
    public static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            char[] inputChars = sc.nextLine().toCharArray();

            for(int j = 0; j < M; j++) {
                map[i][j] = inputChars[j];
            }
        }

        // 해당 영역 내로 파이프가 존재하는지 체크
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            if(setPipe(i, 0))
                count += 1;
        }

        System.out.println(count);
    }

    // 파이프 설치로직
    private static boolean setPipe(int x, int y) {
        // 해당 지점에서 오른쪽, 오른쪽 위 대각선, 아래 대각선
        // 3방향을 탐색

        visited[x][y] = true;

        // 마지막 열에 도착
        if(y == M - 1) {
            return true;
        }

        for(int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && map[nx][ny] == '.')  {
                if(setPipe(nx, ny)) {
                    return true;
                }
            }
        }

        return false;
    }
}
