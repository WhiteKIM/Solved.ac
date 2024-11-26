import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for(int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(input[j]);
                if(num == 0)
                    map[i][j] = 999999;
                else
                    map[i][j] = 1;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    map[j][k] = Math.min(map[j][k], map[j][i]+map[i][k]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] >= 999999)
                     sb.append(0).append(" ");
                else
                    sb.append(1).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
