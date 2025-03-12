import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] number = new int[N];
        int[] nature = new int[N];
        int[] reverse = new int[N];

        String[] input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(input[i]);
            number[i] = num;
            nature[i] = 1;
            reverse[i]= 1;
        }

        // 정방향
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(number[i] > number[j]) {
                    nature[i] = Math.max(nature[i], nature[j] + 1);
                }
            }
        }

        // 역방향
        for(int i = N - 1; i >= 0; i--) {
            for(int j = N - 1; j > i; j--) {
                if(number[i] > number[j]) {
                    reverse[i] = Math.max(reverse[i], reverse[j] + 1);
                }
            }
        }

        int result = 0;

        for(int i = 0; i < N; i++) {
            result = Math.max(result, nature[i] + reverse[i] - 1);
        }

        System.out.println(result);
    }
}
