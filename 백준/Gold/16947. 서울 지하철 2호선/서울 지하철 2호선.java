import java.util.*;

public class Main {
    public static List<List<Integer>> graph = new ArrayList<>();
    public static boolean[] pointList = null;
    public static int N = 0;
    public static int[] distance;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        distance = new int[N+1];

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int X = Integer.parseInt(input[0]);
            int Y = Integer.parseInt(input[1]);

            graph.get(X).add(Y);
            graph.get(Y).add(X);
        }

        for(int i = 1; i <= N; i++) {
            pointList = new boolean[N+1];

            if(findCycle(i, i, i)) {
                break;
            }
        }

        // 1 -> 3 -> 4 -> 2 -> 1

        // 순환역 사이 거리 계산
        for(int i = 1; i <= N; i++) {
            if(!pointList[i]) {
                int n = calDistance(i);
                distance[i] = n;
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(distance[i] + " ");
        }
    }

    private static boolean findCycle(int prev, int now, int start) {
        pointList[now] = true;

        for(int nx : graph.get(now)) {
            if(!pointList[nx]) {
                if(findCycle(now, nx, start)) {
                    return true;
                }
            } else if(prev != nx && nx == start) {
                return true;
            }
        }

        pointList[now] = false;

        return false;
    }

    private static int calDistance(int i) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        queue.add(new int[]{i, 0});
        visited[i] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int X = arr[0];
            int dist = arr[1];

            if(pointList[X]) {
                return dist;
            }

            for(int nx : graph.get(X)) {
                if(!visited[nx]) {
                    visited[nx] = true;
                    queue.add(new int[]{nx, dist + 1});
                }
            }
        }

        return 0;
    }
}
