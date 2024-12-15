import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int M = Integer.parseInt(sc.nextLine());

        ArrayList<ArrayList<Integer>> friends = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            friends.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            String[] input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            friends.get(x).add(y);
            friends.get(y).add(x);
        }

        solved(1, friends, N);
        System.out.println(count);
    }

    public static void solved(int start, ArrayList<ArrayList<Integer>> graph, int N) {
        boolean[] visited = new boolean[N+1];
        Queue<int[]> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int f = arr[1];


            for(int i : graph.get(x)) {
                if(!visited[i] && f < 2) {
                    visited[i] = true;
                    count++;
                    queue.add(new int[]{i, f+1});
                }
            }
        }
    }
}
