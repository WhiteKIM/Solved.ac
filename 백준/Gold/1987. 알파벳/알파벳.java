import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static boolean[] alphabets = new boolean[26];
    public static int N = 0;
    public static int M = 0;
    public static char[][] map;
    public static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        solved(0, 0, 1);

        System.out.println(count);
    }

    public static void solved(int x, int y, int cnt) {
        if(0 > x || x >= N || 0 > y || y >= M || alphabets[map[x][y] - 'A']) {
            return;
        }

        alphabets[map[x][y] - 'A'] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            solved(nx, ny, cnt + 1);
        }

        alphabets[map[x][y] - 'A'] = false;

        count = Math.max(count, cnt);
    }
}
