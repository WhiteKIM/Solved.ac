import java.io.*;

public class Main {
    public static int N;
    public static int M;
    public static boolean[] visited;
    public static int[] data;
    public static boolean checked = false;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        data = new int[N];
        visited = new boolean[N+1];

        solved(0);

        br.close();
        bw.close();
    }

    public static void solved(int count) throws IOException {
        if(count == N) {
            if(!checked && calc()) {
                for(int i : data) {
                    bw.write(i+" ");
                }
                checked = true;
            }
            return;
        }

        if(checked)
            return;

        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                data[count] = i;
                solved(count+1);
                data[count] = 0;
                visited[i] = false;
            }
        }
    }

    public static boolean calc() {
        int[] result = new int[N];
        for(int i = 0; i < N; i++) {
            result[i] = data[i];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < N - 1; j++) {
                result[j] = result[j] + result[j + 1];
            }
        }

        return result[0] == M;
    }
}
