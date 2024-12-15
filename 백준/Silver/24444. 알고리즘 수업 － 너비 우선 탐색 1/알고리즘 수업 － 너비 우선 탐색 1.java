import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int count = 1;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int start = Integer.parseInt(input[2]);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for(int i = 1; i <= N; i++) {
            graph.get(i).sort(Comparator.naturalOrder());
        }

        solved(start, graph, N);
    }

    public static void solved(int start, ArrayList<ArrayList<Integer>> graph, int N) {
        boolean[] visited = new boolean[N+1];
        int[] orders = new int[N+1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        orders[start] = count++;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for(int i : graph.get(x)) {
                if(!visited[i]) {
                    visited[i] = true;
                    orders[i] = count++;
                    queue.add(i);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(orders[i]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
