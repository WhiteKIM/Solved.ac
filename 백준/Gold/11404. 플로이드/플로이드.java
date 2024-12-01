import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    public static int[][] result;
    public static int big = 9999999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        result = new int[N+1][N+1];

        for(int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            graph.get(from).add(new int[]{to, weight});
        }

        solved(N);

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int num = result[i][j];

                if(num >= big)
                    num = 0;

                System.out.print(num+ " ");
            }
            System.out.println();
        }
    }

    private static void solved(int N) {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i != j) {
                    result[i][j] = big;
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < graph.get(i).size(); j++) {
                int[] arr = graph.get(i).get(j);
                int to = arr[0];
                int weight = arr[1];

                result[i][to] = Math.min(result[i][to], weight);
            }
        }

        for(int k = 1; k <= N; k++) {
            for(int x = 1; x <= N; x++) {
                for(int y = 1; y <= N; y++) {
                    result[x][y] = Math.min(result[x][y], result[x][k] + result[k][y]);
                }
            }
        }
    }
}
