import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 주어진 수열을 묶는 것과 더하는 것을 적절히 조합해서
        // 최대수를 만들자

        // 2 이상의 수라면 곱하는 것이 무조건 이득
        // 1 이하의 수는 더하는 것이 최대
        // -1 이하의 수는 곱하는 것이 이득,
        // 0은 -1 이하 수가 존재할 때 해당 값과 곱해서 제거

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        int result = 0;

        for(int i = 0; i < N; i++) {
            int num = sc.nextInt();

            if(num == 1) {
                // 1은 더하는게 무조건 이익
                result += 1;
            } else if(num > 1) {
                plus.add(num);
            } else {
                minus.add(num);
            }
        }

        plus.sort(Comparator.reverseOrder());
        minus.sort(Comparator.naturalOrder());

        for(int i = 0; i < plus.size(); i++) {
            if(i + 1 < plus.size()) {
                // 해당 부분은 무조건 곱하는 것이 이득
                result += plus.get(i) * plus.get(i + 1);
                i += 1;
            } else {
                // 마지막 남는 값
                result += plus.get(i);
            }
        }

        for(int i = 0; i < minus.size(); i++) {
            if(i + 1 < minus.size()) {
                // 해당 부분은 무조건 곱하는 것이 이득
                result += minus.get(i) * minus.get(i + 1);
                i += 1;
            } else {
                // 마지막 남는 값
                result += minus.get(i);
            }
        }

        System.out.println(result);
    }
}
