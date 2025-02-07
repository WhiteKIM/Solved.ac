import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[] result;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static int N = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        result = new int[N+1];

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int distance = Integer.parseInt(input[2]);

            graph.get(x).add(new Node(y, distance));
            graph.get(y).add(new Node(x, distance));
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            sb.append(solved(x, y)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int solved(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        int[] visited = new int[N+1];

        for(int i = 1; i <= N; i++)
            visited[i] = Integer.MAX_VALUE;

        queue.add(new int[]{x, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int distance = arr[1];

            if(x1 == y) {
                return distance;
            }

            for(Node n : graph.get(x1)) {
                int d1 = distance + n.distance;

                if(d1 < visited[n.point]) {
                    visited[n.point] = d1;
                    queue.add(new int[]{n.point, d1});
                }
            }
        }

        return -1;
    }

    public static class Node {
        int point;
        int distance;

        public Node(int point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }
}
