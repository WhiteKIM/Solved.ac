import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] arr = new int[N];

        String[] input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        // 특정 숫자를 선택해서 앞으로 보낼것인지 뒤로 보낼 것인지 선택해야 한다.
        // 정렬되지 않은 수열을 최소한으로 이동시켜 정렬되도록 하는 방법은
        // 일단 현재 상태에서 가장 연속된 수열을 찾고, 해당 수열에 값을 더하는 식으로
        // 부분 수열을 구성해서 최종적으로 모두 정렬된 수열을 얻는 방식이다.
        int[] length = new int[N + 1];
        int max = 0;

        for(int i = 0; i < N; i++) {
            length[arr[i]] = length[arr[i] - 1] + 1;
            max = Math.max(max, length[arr[i]]);
        }

        System.out.println(N - max);
    }
}
