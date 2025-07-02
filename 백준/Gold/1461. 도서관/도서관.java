import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        List<Integer> negative = new ArrayList<>();
        List<Integer> positive = new ArrayList<>();
        input = sc.nextLine().split(" ");
        int last = 0;

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(input[i]);

            last = Math.max(last, Math.abs(num));

            if(num < 0) {
                negative.add(Math.abs(num));
            } else {
                positive.add(num);
            }
        }

        negative.sort(Comparator.reverseOrder());
        positive.sort(Comparator.reverseOrder());

        int move = 0;

        // 책을 다시 가지러 원점을 가야한다.
        // 따라서 이동거리는 책 돌려놓는 거리 * 2

        for(int i = 0; i < negative.size(); i+=K) {
            move += negative.get(i) * 2;
        }

        for(int i = 0; i < positive.size(); i+=K) {
            move += positive.get(i) * 2;
        }

        System.out.println(move - last);
    }
}
