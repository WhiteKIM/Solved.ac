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

        List<Integer> peoples = new ArrayList<>();

        input = sc.nextLine().split(" ");

        for(String str : input) {
            peoples.add(Integer.parseInt(str));
        }

        List<Integer> group = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            // 옆사람과의 격차 계산
            group.add(peoples.get(i+1) - peoples.get(i));
        }

        // 1  3  5  6  10
        //  2  2  1  4
        // 여기서 가장 작은 수를 N - K개를 선택하면 그룹은 K개가 구성

        int cost = 0;

        group.sort(Comparator.naturalOrder());

        for(int i = 0; i < N - K; i++) {
            cost += group.get(i);
        }

        System.out.println(cost);
    }
}
