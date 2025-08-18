import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        // 항상 물병의 합은 2의 배수값
        // 현재 N을 K개 이하의 물병에 배분가능한지 체크

        int answer = 0;

        if(N <= K) {
            System.out.println(0);
            return;
        }

        for(int i = N; i <= 100000000; i++) {
            int count = shared(i);

            // 배분에 필요한 병 개수 체크
            if(count <= K) {
                break;
            }

            answer++;
        }

        System.out.println(answer);

    }

    public static int shared(int N) {
        int count = 0;

        while (N > 0) {

            if(N % 2 > 0) {
                count+=1;
            }

            N = N / 2;
        }

        return count;
    }
}
