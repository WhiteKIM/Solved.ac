import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static int[][] island;
    public static int[][] visited;
    public static int index = 0;
    // 섬 탐색용
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        island = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                island[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(island[i][j] == 1 && visited[i][j] == 0) {
                    index+=1;
                    divideIsland(i, j);
                }
            }
        }

        for(int i = 0; i <= index; i++) {
            graph.add(new ArrayList<>());
        }

        for(int n = 1; n <= index; n++) {
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < M; y++) {
                    for(int i = 0; i < 4; i++) {
                        if(visited[x][y] == n) {
                            solved(x, y, i, n);
                        }
                    }
                }
            }
        }

        System.out.println(prim());
    }

    public static int prim() {
        boolean[] visited = new boolean[index+1];
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(1, 1, 0));
        int cost = 0;
        int edge = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if(visited[node.to])
                continue;

            visited[node.to] = true;
            cost += node.weight;
            edge++;

            if(edge == index)
                break;

            for(Node n : graph.get(node.to)) {
                if(!visited[n.to]) {
                    queue.add(n);
                }
            }
        }

        for(int i = 1; i <= index; i++) {
            if(!visited[i])
                return -1;
        }

        return cost;
    }

    public static void divideIsland(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        queue.add(new int[]{x, y});
        check[x][y] = true;
        visited[x][y] = index;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!check[nx][ny] && island[nx][ny] == 1) {
                        check[nx][ny] = true;
                        visited[nx][ny] = index;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    public static void solved(int x, int y, int action, int index) {
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        // 점 x, y | 액션, 카운트
        // 1 - 동, 2 - 서 3 - 남, 4 - 북
        queue.add(new int[]{x, y, 0});
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int cnt = arr[2];
            
            int nx = x1 + dx[action];
            int ny = y1 + dy[action];

            if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                // 다른 섬 도착
                if(visited[nx][ny] != 0 &&  visited[nx][ny] != index) {
                    if(cnt >= 2) {
                        graph.get(index).add(new Node(index, visited[nx][ny], cnt));
                    }
                    continue;
                }
                
                // 항해중
                if(!visit[nx][ny] && visited[nx][ny] == 0) {
                    visit[nx][ny] = true;
                    queue.add(new int[]{nx, ny, cnt+1});
                }
            }
        }
    }

    public static class Node implements Comparable{
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            Node node = (Node) o;
            return this.weight - node.weight;
        }
    }
}
