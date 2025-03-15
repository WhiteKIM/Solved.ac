import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final int INF = 99999999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] wallet = new int[K+1];
        List<Integer> coins = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(sc.nextLine());
            coins.add(num);
        }

        for(int i = 0; i <= K; i++) {
            wallet[i] = INF;
        }

        wallet[0] = 0;
        for(int i = 1; i <= K; i++) {

            for(int coin : coins) {
                if(i - coin >= 0) {
                    wallet[i] = Math.min(wallet[i], wallet[i - coin] + 1);
                }
            }
        }

        if(wallet[K] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(wallet[K]);
        }
    }
}
