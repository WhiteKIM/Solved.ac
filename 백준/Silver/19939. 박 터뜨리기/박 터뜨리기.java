import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int sum = 0;

        for(int i = 1; i <= K; i++) {
            sum += i;
        }

        N -= sum;

        if(N < 0) {
            // 주어진 공을 바구니에 각각 다르게 배분 불가능
            System.out.println(-1);
        } else {
            if(N % K == 0) {
                // 주어진 공을 하나씩 올리면서 배분
                System.out.println(K - 1);
            } else {
                // 건너뛰는 수가 존재한다.
                System.out.println(K);
            }
        }
    }
}
