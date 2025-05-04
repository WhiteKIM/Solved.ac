import java.util.*;

public class Main {
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int[][] usado;
    public static int N;
    public static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        usado = new int[N+1][N+1];

        for(int i = 0; i < N - 1; i++) {
            input = sc.nextLine().split(" ");

            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            // 양방향 설정
            graph.get(x).add(y);
            graph.get(y).add(x);

            usado[x][y] = weight;
            usado[y][x] = weight;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            count = 0;  // 초기화
            input = sc.nextLine().split(" ");
            int K = Integer.parseInt(input[0]);
            int V = Integer.parseInt(input[1]);

            solved(K, V);
            sb.append(count).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void solved(int weight, int node) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        visited[node] = true;
        // 탐색점, 탐색카운트
        queue.add(new int[]{node, weight});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int point = arr[0];
            int usa = arr[1];

            for(int next : graph.get(point)) {
                if(!visited[next]) {
                    if(weight <= Math.min(usa, usado[point][next])) {
                        count+=1;
                        visited[next] = true;
                        queue.add(new int[]{next, usado[point][next]});
                    }
                }
            }
        }
    }
}
