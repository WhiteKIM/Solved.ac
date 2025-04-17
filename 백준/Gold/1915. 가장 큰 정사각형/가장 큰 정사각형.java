import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = sc.readLine().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);

        int[][] dp = new int[x+1][y+1];
        int size = 0;

        for(int i = 1; i <= x; i++) {
            char[] inputs = sc.readLine().toCharArray();
            for(int j = 1; j <= y; j++) {
                int num = inputs[j-1] - '0';
                dp[i][j] = num;
                if(dp[i][j] == 1 && size == 0) {
                    size = 1;
                } else {
                    if(dp[i - 1][j - 1] > 0 && dp[i - 1][j] > 0 && dp[i][j - 1] > 0 && dp[i][j] == 1) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1; // 가로 길이
                        size = Math.max(size, dp[i][j]);
                    }
                }
            }
        }

        bw.write(String.valueOf(size * size));
        bw.flush();
        sc.close();
        bw.close();
    }
}
