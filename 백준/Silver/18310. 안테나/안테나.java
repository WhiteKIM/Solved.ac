import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        List<Integer> data = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            data.add(sc.nextInt());
        }

        data.sort(Comparator.naturalOrder());

        // 가운데가 가장 가까운 집을 선택해서 해당 위치에 설치
        System.out.println(data.get((N - 1) / 2));
    }
}
