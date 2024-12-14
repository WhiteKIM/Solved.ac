import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static List<List<int[]>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            graph.get(x).add(new int[]{y, weight});
        }

        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        solved(start, end, N);
    }

    public static void solved(int start, int end, int N) {
        int[] distance = new int[N+1];

        for(int i = 1; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[start] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int v = arr[0];
            int w = arr[1];

            if(distance[v] < w)
                continue;

            for(int[] node : graph.get(v)) {
                int y = node[0];
                int weight = node[1];

                if(distance[y] > distance[v] + weight) {
                    distance[y] = distance[v] + weight;
                    queue.add(new int[]{y, weight});
                }
            }
        }

        System.out.println(distance[end]);
    }
}
