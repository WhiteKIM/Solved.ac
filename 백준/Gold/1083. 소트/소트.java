import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * 주어진 수열을 가능한 내림차순으로 정렬할 때 마지막에 올 수 있는 값으로 구성
     * 수열에서 수는 앞뒤로 인접한 숫자만 위치를 바꿀 수 있음
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        List<Integer> arr = new ArrayList<>();
        String[] input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(input[i]));
        }

        int S = Integer.parseInt(sc.nextLine());

        // 가장 앞서는 숫자를 구성하려면 0번째부터 가장 큰 수가 오도록 정렬해야 함
        // s가 허용하는 한 가장 큰 수를 찾고 해당 값을 앞으로 가져오고 다른 값을 한칸씩 뒤로

        int pos = 0;

        while (S > 0 && pos < N) {
            int max = 0;
            int maxIndex = 0;

            for(int i = pos; i < (Math.min(S + pos + 1, N)) ; i++) {
                if(max < arr.get(i)) {
                    max = arr.get(i);
                    maxIndex = i;
                }
            }

            // 다른 배열의 값을 바꾼 값 위치까지 밀어야해
            arr.remove(maxIndex);
            arr.add(pos, max);

            S -= maxIndex - pos;      // 이동한 만큼 횟수를 감함
            pos++;              // 다음에 변경할 값 위치 업데이트
        }

        for(int a : arr) {
            System.out.print(a + " ");
        }
    }
}
