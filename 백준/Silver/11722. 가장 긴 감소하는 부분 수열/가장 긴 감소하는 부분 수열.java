import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] data = new int[N];
        int[] result = new int[N];
        String[] input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(input[i]);
        }

        result[0] = 1;
        int length = 0;

        for(int i = 1; i < N; i++) {
            result[i] = 1; // 자신만

            for(int j = 0; j < i; j++) {
                if(data[j] > data[i]) {
                    result[i] = Math.max(result[i], result[j] + 1);
                }
            }
        }

        for(int i : result) {
            length = Math.max(length, i);
        }

        System.out.println(length);
    }
}
