import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 상대와 동일한 시간에 동일한 위치에 도달할 수 있는지 계산한다.
 * 만약 상대가 오기로 예측된 지점에 일찍 도착하였다면, 주변을 배회하면서 대기
 * 배회가 가능하고, 해당 배회에는 2초라는 시간이 소요됨
 * 따라서 상대가 홀수초에 도착하면 술래도 홀수초에 도착해야 배회하면서 붙잡을 수 있음
 * 짝수, 홀수 두 경우로 나누어 보면서 상대가 도달하는지 파악
 */
public class Main {
    public static int N;
    public static int K;
    public static Action[] actions = new Action[3];
    public static boolean[][] visited = new boolean[500001][2];
    public static int time = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        actions[0] = new Action(1, "WALK");
        actions[1] = new Action(-1, "WALK");
        actions[2] = new Action(2, "TP");

        solved();
        
        bw.write(String.valueOf(time));
        bw.close();
        br.close();
    }

    /*
     * 술래는 자신이 있었던 위치로 다시 되돌아올 수 있다.
     * 
     */
    public static void solved() {
        Queue<int[]> queue = new LinkedList<>();
        // 내위치, 상대위치, 진행시간
        // 상대위치는 상대위치 + 진행시간
        queue.add(new int[]{N, 0});
        visited[N][0] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int n1 = arr[0];
            int ss = arr[1];
            int newK = K + (ss * (ss + 1)) / 2;

            if(newK > 500000) {
                return;
            }

            if(visited[newK][ss%2]) {
                time = ss;
                return;
            }

            for(int i = 0; i < 3; i++) {
                Action act = actions[i];
                int nx;
                int nt = ss + 1;

                if(act.action.equals("WALK")) {
                    nx = n1 + act.move;
                } else {
                    nx = n1 * 2;
                }

                if(0 <= nx && nx <= 500000) {
                    if(!visited[nx][nt%2]) {
                        visited[nx][nt%2] = true;
                        queue.add(new int[]{nx, nt});
                    }
                }
            }
        }
    }

    public static class Action {
        int move;
        String action;

        public Action(int move, String action) {
            this.move = move;
            this.action = action;
        }
    }
}
