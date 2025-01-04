import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[] result = new long[91];
        result[1] = 1;
        result[2] = 1;

        for(int i = 3; i <= 90; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }

        System.out.println(result[N]);
    }
}
