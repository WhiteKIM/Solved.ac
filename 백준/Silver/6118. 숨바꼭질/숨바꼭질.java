import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int N;
    public static int[] result;
    public static ArrayList<ArrayList<Integer>> map = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        result = new int[N+1];

        for(int i = 0; i <= N; i++) {
            map.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            map.get(x).add(y);
            map.get(y).add(x);
        }

        solved();

        int maxNum = 0;

        for(int i = 1; i <= N; i++) {
            if(result[i] != Integer.MAX_VALUE) {
                maxNum = Math.max(result[i], maxNum);
            }
        }

        int min = 9999999;
        int maxCnt = 0;

        for(int i = 1; i <= N; i++) {
            if(maxNum == result[i]) {
                maxCnt+=1;
                min = Math.min(i, min);
            }
        }

        System.out.println(min+" "+maxNum + " " +maxCnt);
    }

    public static void solved() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        for(int i = 2; i <= N; i++) {
            result[i] = Integer.MAX_VALUE;
        }

        result[1] = 0;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int next = arr[0];
            int cnt = arr[1];

            for(int v : map.get(next)) {
                if(!visited[v]) {
                    visited[v] = true;
                    result[v] = Math.min(result[v], cnt + 1);
                    queue.add(new int[]{v, cnt + 1});
                }
            }
        }
    }
}
