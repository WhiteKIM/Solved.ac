import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] result = new int[N * 2 + 1];
        result[1] = 1;
        result[2] = 3;

        for(int i = 3; i <= N * 2; i++) {
            result[i] = (result[i - 1] + result[ i - 2] * 2) % 10007;
        }

        System.out.println(result[N] % 10007);
    }
}
