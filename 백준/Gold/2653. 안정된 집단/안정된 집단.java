import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        arr = new int[N+1];
        int[][] data = new int[N][N]; // 검증 데이터
        boolean check = true;

        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        for(int i = 1; i <= N; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 1; j <= N; j++) {
                int n = Integer.parseInt(input[j - 1]);
                data[i - 1][ j - 1] = n;
                if(i != j && n == 0) {
                    union(i, j);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(data[i][j] != data[j][i]) {
                    check = false;
                    break;
                }
            }

            if(!check)
                break;
        }

        ArrayList<Integer> count = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(!count.contains(arr[i]))
                count.add(arr[i]);
        }

        for(int num : count) {
            int cnt = 0;
            for(int i = 1; i <= N; i++) {
                if(arr[i] == num) {
                    cnt++;
                }
            }

            if(cnt == 1) { // 나홀로 그룹
                check = false;
            }
        }

        if(check) {

            Collections.sort(count);

            StringBuilder sb = new StringBuilder();
            for(int num : count) {
                for(int i = 1; i <= N; i++) {
                    if(arr[i] == num) {
                        sb.append(i).append(" ");
                    }
                }
                sb.append("\n");
            }

            System.out.println(count.size());
            System.out.println(sb.toString());
        } else {
            System.out.println(0);
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        arr[y] = x;
    }

    public static int find(int x) {
        if(arr[x] == x) {
            return x;
        } else {
            return find(arr[x]);
        }
    }
}
