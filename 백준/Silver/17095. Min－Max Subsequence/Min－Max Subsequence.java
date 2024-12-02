import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] data = new int[N];

        String[] input = sc.nextLine().split(" ");
        for(int i = 0; i < N; i++) {
            int num  = Integer.parseInt(input[i]);
            data[i] = num;


        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int diff = Integer.MIN_VALUE;

        int left = -1;
        int right = -1;
        int length = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            // 동일한 값이 쭉 이어질 수 있다.
            if(max <= data[i]) {
                max = data[i];
                right = i;
            }

            // 동일한 값이 쭉 이어질 수 있다.
            if(min >= data[i]) {
                min = data[i];
                left = i;
            }

            // 차가 최대가 되는 수열 중에서 길이가 가장 짧은 경우를 구한다.
            // 역순 정렬 수열이 있는 듯
            if(max - min > diff) {
                diff = max - min;
                length = Math.abs(right - left) + 1;
            } else if(max - min == diff) {// 이전 차와 같지만, 길이가 더 짤다면 해당 값 사용
                length = Math.min(length, Math.abs(right - left) + 1);
            }
        }


        System.out.println(length);
    }
}
