import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        long[] num = new long[N];
        input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            num[i] = Long.parseLong(input[i]);
        }

        // 가장 작은 두 값을 선택해서 두 값을 더하고
        // 해당 값으로 더한 두 값의 위치에 덮어쓴다.

        for(int i = 0;  i < M; i++) {
            int left = -1;
            long leftMin = Long.MAX_VALUE;
            int right = -1;
            long rightMin = Long.MAX_VALUE;

            // 가장 작은 값 하나 선택
            for(int j = 0; j < N; j++) {
                if(num[j] < leftMin) {
                    leftMin = num[j];
                    left = j;
                }
            }

            // 선택된 가장 작은값과 같거나 조금만 더 큰 값을 찾는다.
            for(int j = 0; j < N; j++) {
                if(num[j] < rightMin && j != left) {
                    rightMin = num[j];
                    right = j;
                }
            }

            // 좌우 두값을 더한 값을 배열에 반영
            num[left] = leftMin + rightMin;
            num[right] = leftMin + rightMin;
        }

        long result = 0L;

        for(int i = 0; i < N; i++) {
            result += num[i];
        }

        System.out.println(result);
    }
}
