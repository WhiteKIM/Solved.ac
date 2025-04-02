import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[][] matrix = new int[N+1][N+1];
        int[][] arr = new int[N+1][2];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            arr[i+1][0] = x;
            arr[i+1][1] = y;
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i = 2; i <= N; i++) {
            for(int j = 1; j <= N - i + 1; j++) {
                int n = i + j - 1;
                for(int k = j; k <= n - 1; k++) {
                    int cost = matrix[j][k] + matrix[k+1][n] + arr[j][0] * arr[k][1] * arr[n][1];
                    matrix[j][n] = Math.min(cost, matrix[j][n]);
                }
            }
        }

        System.out.println(matrix[1][N]);
    }
}
