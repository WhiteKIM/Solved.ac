import java.util.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] result;
    public static int point = Integer.MAX_VALUE;
    public static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        result = new int[N+1];

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        while (true) {
            String[] input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            if(x == -1 && y == -1) {
                break;
            }

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for(int i = 1; i <= N; i++) {
            cnt = 0;
            solved(i, N);
            result[i] = cnt;
            point = Math.min(cnt, point);
        }

        int count = 0;
        for(int i = 1; i <= N; i++) {
            if(result[i] == point) {
                count+=1;
            }
        }

        System.out.println(point+" "+count);

        for(int i = 1; i <= N; i++) {
            if(result[i] == point) {
                System.out.print(i+" ");
            }
        }
    }

    public static void solved(int start, int length) {
        boolean[] visited = new boolean[length+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int temp = arr[1];

            if(visited[x]) {
                continue;
            }

            visited[x] = true;
            cnt = Math.max(cnt, temp);

            for(int nx : graph.get(x)) {
                if(!visited[nx]) {
                    queue.add(new int[]{nx, temp+1});
                }
            }
        }
    }
}
