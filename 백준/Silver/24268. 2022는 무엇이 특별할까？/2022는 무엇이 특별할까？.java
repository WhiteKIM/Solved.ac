import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static ArrayList<int[]> data = new ArrayList<>();

    public static void main(String[] args) {
        long N;
        int D;
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" ");
        N = Long.parseLong(input[0]);
        D = Integer.parseInt(input[1]);

        int[] arr = new int[D];

        for(int i = 0; i < D; i++) {
            arr[i] = i;
        }

        int[] output = new int[D];
        boolean[] visited = new boolean[D];

        permutation(arr, output, visited, 0, D);

        long num = Long.MAX_VALUE;

        for(int[] a : data) {
            if(!isValidation(a, D)) {
                continue;
            }

            long val = convertDigit(a, D);

            if(N < val) {
                num = Math.min(num, val);
            }
        }

        if(num != Long.MAX_VALUE) {
            System.out.println(num);
        } else {
            System.out.println(-1);
        }
    }

    private static long convertDigit(int[] a, int D) {
        long val = 0;
        for(int i = a.length - 1; i >= 0; i--) {
            val += a[i] * Math.pow(D, i);
        }

        return val;
    }

    private static boolean isValidation(int[] a, int D) {
        boolean[] visited = new boolean[D];

        for(int i = a.length - 1; i >= 0; i--) {
            if(a[a.length - 1] == 0)
                continue;
            visited[a[i]] = true;
        }

        for(int i = 0; i < D; i++) {
            if(!visited[i]) {
                return false;
            }
        }

        return true;
    }

    public static void permutation(int[] number, int[] output, boolean[] visited, int depth, int r) {
        if(depth == r) {
            data.add(output.clone());
            return;
        }

        for(int i = 0; i < number.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                output[depth] = number[i];
                permutation(number, output, visited, depth+1, r);
                visited[i] = false;
            }
        }
    }
}
