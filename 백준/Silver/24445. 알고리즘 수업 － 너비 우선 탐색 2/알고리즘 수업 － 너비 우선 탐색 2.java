
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int M;
    public static int count = 1;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] orders;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int V = Integer.parseInt(input[2]);

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }

        orders = new int[N+1];

        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for(int i = 1; i <= N; i++) {
            graph.get(i).sort(Comparator.reverseOrder());
        }

        solved(V);

        for(int i = 1; i <= N; i++) {
            System.out.println(orders[i]);
        }
    }

    public static void solved(int v) {
        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList<>();
        visited[v] = true;
        orders[v] = count++;
        queue.add(v);

        while (!queue.isEmpty()) {
            int n = queue.poll();

            for(int next : graph.get(n)) {
                if(!visited[next]) {
                    visited[next] = true;
                    orders[next] = count++;
                    queue.add(next);
                }
            }
        }
    }
}
