import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int packPrice = Integer.MAX_VALUE;
        int eachPrice = Integer.MAX_VALUE;

        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");
            int pack = Integer.parseInt(input[0]);
            int each = Integer.parseInt(input[1]);

            packPrice = Math.min(pack, packPrice);
            eachPrice = Math.min(each, eachPrice);
        }

        // 가장 가성비있게 기타줄을 구매하는 방법
        // 끊어진 줄이 6개 이상일 떄, 6줄 패키지에서 가장 제품으로 구매하고
        // 나머지는 낱개 중에서 가장 싼 제품을 구매한다.
        int money = 0;
        int packCount = N / 6;  // 패키지로 구매할 개수
        int eachCount = N % 6;  // 낱개로 구매할 개수

        money = (packCount * packPrice) + (eachCount * eachPrice);

        // 패키지로만 구매할 때 더 가격이 쌀 수도 있다.
        money = Math.min(money, (packCount + 1) * packPrice);

        // 낱개를 더 싸게 팔수도 있을까?
        money = Math.min(money, N * eachPrice);

        System.out.println(money);
    }
}
