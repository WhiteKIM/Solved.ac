import java.util.Scanner;

public class Main {
    public static int[][] map;
    public static int[][] dp;
    public static int count = 0;
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }


        // 끝까지 이동가능한 경우만 세어주어야 한다.
        solved(0, 1, 0);

        System.out.println(count);
    }

    public static void solved(int x, int y, int direction) {
        if(x == N - 1 && y == N - 1) {
            count+=1;
            return;
        }

        // 가로
        if(direction == 0) {

            if(y + 1 < N && map[x][y+1] == 0) {
                solved(x, y+1, 0);
            }

            if(isMovable(x, y)) {
                solved(x+1, y+1, 2);
            }
        } else if(direction == 1) { // 세로
            if(x + 1 < N && map[x+1][y] == 0) {
                solved(x+1, y, 1);
            }

            if(isMovable(x, y)) {
                solved(x+1, y+1, 2);
            }
        } else { // 대각선
            if(y + 1 < N && map[x][y+1] == 0) {
                solved(x, y+1, 0);
            }

            if(x + 1 < N && map[x+1][y] == 0) {
                solved(x+1, y, 1);
            }

            if(isMovable(x, y)) {
                solved(x+1, y+1, 2);
            }
        }
    }

    //  대각선으로 움직일 때 범위 내 벽포함여부 체크
    public static boolean isMovable(int x, int y) {
        if(!(y + 1 < N && map[x][y + 1] == 0))
            return false;

        if(!(x + 1 < N && map[x + 1][y] == 0))
            return false;

        if(!(x + 1 < N && y + 1 < N && map[x + 1][y + 1] == 0))
            return false;

        return true;
    }
}
