import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[] distance;
    public static int N;
    public static int M;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int start = Integer.parseInt(br.readLine());
        distance = new int[N+1];

        for(int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);

            // 단방향 연결
            graph.get(x).add(new Node(y, w));
        }

        solved(start);
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(distance[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void solved(int start) {
        for(int i = 1; i <= N; i++)
            distance[i] = Integer.MAX_VALUE;

        distance[start] = 0;
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int point = curNode.to;
            int weight = curNode.weight;

            if(distance[point] < weight)
                continue;

            for(Node node : graph.get(point)) {
                int nextPoint = node.to;
                int nextWeight = node.weight + distance[point];

                if(nextWeight < distance[nextPoint]) {
                    distance[nextPoint] = nextWeight;
                    queue.add(new Node(nextPoint, nextWeight));
                }
            }
        }
    }

    public static class Node implements Comparable{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            Node comp = (Node) o;
            return Integer.compare(this.weight, comp.weight);
        }
    }
}