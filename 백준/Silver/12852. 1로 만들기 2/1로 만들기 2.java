import java.util.Scanner;

public class Main {
    public static int[] result = new int[1000001];
    public static String[] path = new String[1000001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for(int i = 2; i <= 1000000; i++) {
            result[i] = i;
        }

        path[1] = "1";

        for(int i = 2; i <= N; i++) {
            int index = -1;

            if(result[i-1]+1 < result[i]) {
                result[i] = result[i-1]+1;
                index = i - 1;
            }

            if(i % 3 == 0 && result[i/3]+1 < result[i]) {
                result[i] = result[i/3]+1;
                index = i / 3;
            }

            if (i % 2 == 0 && result[i/2]+1 < result[i]) {
                result[i] = result[i/2]+1;
                index = i / 2;
            }

            path[i] = i + " " + path[index];
        }

        System.out.println(result[N]);
        System.out.println(path[N]);
    }
}
