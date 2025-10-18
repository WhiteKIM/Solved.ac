import java.util.*;

public class Main {
    public static boolean check = false;
    public static int SUM = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        int C = Integer.parseInt(input[2]);

        List<List<Integer>> list = new ArrayList<>();

        SUM = A + B + C;

        for(int i = 0; i < 6; i++) {
            list.add(new ArrayList<>());
        }

        // 0 1 | 0 2 | 1 2
        if(solved(Integer.parseInt(input[0]), Integer.parseInt(input[1]))) {
            System.out.println(1);
            check = true;
        }

        if(!check)
            System.out.println(0);
    }

    public static boolean solved(int A, int B) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[SUM+1][SUM+1];
        visited[A][B] = true;
        queue.add(new int[]{A, B});

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();

            int X = Math.min(arr[0], arr[1]);
            int Y = Math.max(arr[0], arr[1]);
            int Z = SUM - X - Y;
            
            // 모든 그룹이 공평하게 분배됨
            if (X == Y && X == Z)
                return true;

            // 3개의 조합을 모두 고려해서 분기처리해야 한다.
            for(int[] comb : new int[][]{{X, Y}, {X, Z}, {Y, Z}}) {
                int x1 = Math.min(comb[0], comb[1]);
                int y1 = Math.max(comb[0], comb[1]);
                int z1 = SUM - x1 - y1;

                // 분배 처리
                int nx = x1 + x1;
                int ny = y1 - x1;
                int nz = SUM - x1 - y1;

                // 분배 결과에서 다시 그룹 재선택
                int dx = Math.min(nx, Math.min(ny, nz));
                int dy = Math.max(nx, Math.max(ny, nz));
                int dz = nz;

                if(0 <= dx && dx <= SUM && 0 <= dy && dy <= SUM && !visited[dx][dy]) {
                    visited[dx][dy] = true;
                    queue.add(new int[]{dx, dy});
                }
            }
        }

        return false;
    }
}
