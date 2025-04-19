import java.util.Scanner;

public class Main {
    public static int N;
    public static int[][] graph;
    public static int[][] cost;
    public static int INF = 99999999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        int dist = (1 << N) - 1;
        graph = new int[N][N];
        cost = new int[N][dist];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < dist; j++) {
                cost[i][j] = -1;
            }
        }

        System.out.println(solved(0, dist - 1));
    }

    // 해당 도시를 바로 가는 방법과 경유하는 방법 중에서 선택
    public static int solved(int x, int visited) {
        if(visited == 0) {
            return graph[x][0] == 0 ? INF : graph[x][0];
        }

        // 이전에 경유한 경로라면 저장한 값을 반환하여 불필요연산 제거
        if(cost[x][visited] != -1) {
            return cost[x][visited];
        }

        int min = INF;
        cost[x][visited] = INF;

        for(int i = 0; i < N; i++) {
            // 방문 불가
            if(((visited & (1 << i)) == 0) || graph[x][i] == 0)
                continue;

            int next = visited ^ (1 << i);
            int prev = solved(i, next);
            min = Math.min(min, prev + graph[x][i]);
        }

        return cost[x][visited] = min;
    }
}
