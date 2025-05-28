import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int K = Integer.parseInt(sc.nextLine());
        List<Integer> list = new ArrayList<>();
        String[] input = sc.nextLine().split(" ");

        for(String val : input) {
            list.add(Integer.parseInt(val));
        }

        // 집중국 K개가 최소한 하나의 센서와 통신할 수 있음
        // 결론 : K개의 집중국으로 모든 센서를 커버칠 때 가능한 최소길이
        // 기지국을 최대한 효율적으로 분배하려면
        // 최소 거리에서 최대한 많은 센서를 커버해야한다
        // 3 | 6 7 8 | 10 12 14 15 | 18 20

        Collections.sort(list);

        List<Integer> distance = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            distance.add(list.get(i + 1) - list.get(i));
        }

        Collections.sort(distance);

        int result = 0;

        for(int i = 0; i < N - K; i++) {
            result += distance.get(i);
        }

        System.out.println(result);
    }
}
