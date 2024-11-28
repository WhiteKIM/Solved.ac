import java.util.Scanner;

public class Main {
    public static int[][] peer;
    public static int N = 0;
    public static int P = 0;
    public static int W = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        P = Integer.parseInt(input[2]);

        peer = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) {
                    peer[i][j] = 0;
                } else {
                    peer[i][j] = 99999999;
                }
            }
        }

        for(int i = 0; i < W; i++) {
            input = sc.nextLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;
            int weight = Integer.parseInt(input[2]);

            // 양방향 연결 설정
            peer[from][to] = weight;
            peer[to][from] = weight;
        }

        solved();

        for(int i = 0; i < P; i++) {
            input = sc.nextLine().split(" ");
            int from = Integer.parseInt(input[0]) - 1;
            int to = Integer.parseInt(input[1]) - 1;
            sb.append(peer[from][to]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void solved() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    peer[i][j] = Math.min(peer[i][j], peer[i][k] + peer[k][j]);
                }
            }
        }
    }
}
