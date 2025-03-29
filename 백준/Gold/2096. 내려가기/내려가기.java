import java.util.Scanner;

public class Main {
    public static final int INF = 99999999;
    public static int[] min;
    public static int[] max;
    public static int[][] arr;
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        arr = new int[N][3];
        min = new int[3];
        max = new int[3];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        for(int i = 0; i < 3; i++) {
            min[i] = arr[0][i];
            max[i] = arr[0][i];
        }

        for(int i = 1; i < N; i++) {
            int prevMinLeft = min[0];
            int prevMinRight = min[2];

            int prevMaxLeft = max[0];
            int prevMaxRight = max[2];
            // 0, 2번쨰 값은 매번 선택가능한 경우가 아님
            // 중앙값은 매번 선택되는 값
            // 왼쪽
            min[0] = Math.min(min[0], min[1]) + arr[i][0];
            max[0] = Math.max(max[0], max[1]) + arr[i][0];
            // 오른쪽
            min[2] = Math.min(min[1], min[2]) + arr[i][2];
            max[2] = Math.max(max[1], max[2]) + arr[i][2];
            // 중앙
            min[1] = Math.min(Math.min(prevMinRight, prevMinLeft), min[1]) + arr[i][1];
            max[1] = Math.max(Math.max(prevMaxRight, prevMaxLeft), max[1]) + arr[i][1];
        }

        int minVal = INF;
        int maxVal = 0;

        for(int i = 0; i < 3; i++) {
            minVal = Math.min(minVal, min[i]);
            maxVal = Math.max(maxVal, max[i]);
        }

        System.out.println(maxVal + " " + minVal);
    }
}
