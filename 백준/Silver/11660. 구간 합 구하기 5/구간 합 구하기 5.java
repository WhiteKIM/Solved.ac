import java.util.Scanner;

public class Main {
    public static int N = 0;
    public static int M = 0;
    public static int[][] data;
    public static int[][] result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        data = new int[N][N];
        result = new int[N][N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(input[j]); 
            }
        }

        result[0][0] = data[0][0];

        // x, y지점까지의 합
        for(int i = 1; i < N; i++) {
            result[i][0] = result[i-1][0] + data[i][0];
            result[0][i] = result[0][i-1] + data[0][i];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 1; j < N; j++) {
                result[i][j] = result[i-1][j] + result[i][j-1] -result[i-1][j-1] + data[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");
            int x1 = Integer.parseInt(input[0]) - 1;
            int y1 = Integer.parseInt(input[1]) - 1;
            int x2 = Integer.parseInt(input[2]) - 1;
            int y2 = Integer.parseInt(input[3]) - 1;

            int result = solved(x1, y1, x2, y2);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int solved(int x1, int y1, int x2, int y2) {
        int total = result[x2][y2];
        int top = 0;
        int left = 0;
        int common = 0;

        if(x1 - 1 >= 0) {
            top = result[x1 - 1][y2];
        }

        if(y1 -1 >= 0) {
            left = result[x2][y1 - 1];
        }

        if(x1 - 1 >= 0 && y1 - 1 >= 0) {
            common = result[x1 - 1][y1 - 1];
        }
        
        return  total- top - left + common; 
    }
}
