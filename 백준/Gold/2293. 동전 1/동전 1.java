import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int[] data = new int[10001];
        List<Integer> coins = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int coin = Integer.parseInt(sc.nextLine());
            coins.add(coin);
        }

        data[0] = 1;

        for(int coin : coins) {
            for(int i = coin; i <= K; i++) {
                if(i - coin >= 0) {
                    data[i] += data[i - coin];
                }
            }
        }

        System.out.println(data[K]);
    }
}
