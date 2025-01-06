import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        Profit[] data = new Profit[N];
        int[] result = new int[N+1];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            data[i] = new Profit(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        int max = 0;

        for(int i = 0; i < N; i++) {
            result[i] = Math.max(max, result[i]);

            if(i+data[i].day <= N) {
                result[i+data[i].day] = Math.max(result[i+data[i].day], result[i] + data[i].benefit);
            }

            max = Math.max(result[i], max);
        }

        System.out.println(Math.max(max, result[N]));
    }

    public static class Profit {
        int day;
        int benefit;

        public Profit(int day, int benefit) {
            this.day = day;
            this.benefit = benefit;
        }
    }
}
