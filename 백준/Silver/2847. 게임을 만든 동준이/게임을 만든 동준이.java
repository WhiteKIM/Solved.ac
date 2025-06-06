import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] numbers = new int[N];

        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(sc.nextLine());
        }

        int count = 0;

        for(int i = N - 2; i >= 0; i--) {
            if(numbers[i + 1] <= numbers[i]) {
                count += numbers[i] - numbers[i + 1] + 1;
                numbers[i] = numbers[i + 1] - 1;
            }
        }

        System.out.println(count);
    }
}
