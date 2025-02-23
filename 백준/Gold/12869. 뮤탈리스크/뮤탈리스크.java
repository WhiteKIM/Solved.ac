import java.util.*;

public class Main {
    public static int N = 0;
    public static int[][] attack = {
            {9, 3, 1},
            {9, 1, 3},
            {3, 9, 1},
            {3, 1, 9},
            {1, 3, 9},
            {1, 9, 3},
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().split(" ");
        int[] scvs = new int[3];

        for(int i = 0; i < N; i++) {
            scvs[i] = Integer.parseInt(input[i]);
        }

        int solved = solved(scvs);
        System.out.println(solved);
    }

    private static int solved(int[] scvs) {
        Queue<int[]> queue = new LinkedList<>();
        int[][][] visited = new int[61][61][61];

        for(int i = 0; i <= 60; i++) {
            for(int j = 0; j <= 60; j++) {
                for(int k = 0; k <= 60; k++) {
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        int n1 = scvs[0];
        int n2 = scvs[1];
        int n3 = scvs[2];
        visited[n1][n2][n3] = 0;
        queue.add(new int[]{n1, n2, n3});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int s1 = arr[0];
            int s2 = arr[1];
            int s3 = arr[2];

            for(int i = 0; i < 6; i++) {
                int ns1 = s1 - attack[i][0];
                int ns2 = s2 - attack[i][1];
                int ns3 = s3 - attack[i][2];

                ns1 = Math.max(ns1, 0);
                ns2 = Math.max(ns2, 0);
                ns3 = Math.max(ns3, 0);

                if(visited[s1][s2][s3] + 1 < visited[ns1][ns2][ns3]) {
                    visited[ns1][ns2][ns3] = visited[s1][s2][s3] + 1;
                    queue.add(new int[]{ns1, ns2, ns3});
                }
            }
        }

        return visited[0][0][0];
    }
}
