import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        long[] data = new long[N];

        input = sc.nextLine().split(" ");
        for(int i = 0; i < N; i++) {
            data[i] = Long.parseLong(input[i]);
        }

        long count = 0L;

        for(int i = 1; i < N; i++) {// 다음 값은 등차수열 범위 내
            if(data[i] - data[0] <= ((long) K * i)) {
                count += ((long) K * i) - (data[i] - data[0]);
            } else {
                long num = (data[i] - data[0]) - ((long) K * i);
                count += num * i; // 이전 값들도 해당 값만큼 증가해야 한다.
                data[0] += num; // 첫번째 값에 증가분 반영
            }
        }

        System.out.println(count);
    }
}