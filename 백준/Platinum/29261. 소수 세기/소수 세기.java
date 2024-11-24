import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static boolean[] visited = new boolean[3000001];
    public static int[] data = new int[3000001];
    public static ArrayList<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        primeValidation();

        System.out.println(solved(N));
    }

    public static int solved(int N) {
        if(N == 2 || N == 3) {
            return 1;
        }

        for(int i = N/2; i >= 0; i--) {
            int j = N - i - 1;

            if(visited[i] && visited[j]) {
                return solved(i) + solved(j) + 1;
            }
        }

        return 0;
    }

    public static void primeValidation() {
         visited = new boolean[3000001];

        for(int i = 2; i <= 3000000; i++) {
            visited[i] = true;
            data[i] = 1;
        }

        for(int i = 2; i * i <= 3000000; i++) {
            if(visited[i]) {
                for(int j = i * i; j <= 3000000; j+=i) {
                    visited[j] = false;
                }
            }
        }

        for(int i = 2; i <= 3000000; i++) {
            if(visited[i]) {
                primeList.add(i);
            }
        }
    }
}
