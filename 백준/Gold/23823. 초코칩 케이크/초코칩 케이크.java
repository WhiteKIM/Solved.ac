import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[] cakeVertical = new int[N+1];
        int[] cakeHorizontal = new int[N+1];

        // 최대값 계산
        int verticalMax = Integer.MIN_VALUE;
        int horizontalMax = Integer.MIN_VALUE;
        int vertical = 0;
        int horizontal = 0;

        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");

            int cmd = Integer.parseInt(input[0]);
            int position = Integer.parseInt(input[1]);

            if(cmd == 1) {  // 가로
                cakeHorizontal[position]+=1;

                if(cakeHorizontal[position] > horizontalMax) {
                    horizontalMax = cakeHorizontal[position];
                    horizontal = 1;
                } else if(cakeHorizontal[position] == horizontalMax) {
                    horizontal+=1;
                }

            } else { // 세로
                cakeVertical[position]+=1;

                if(cakeVertical[position] > verticalMax) {
                    verticalMax = cakeVertical[position];
                    vertical = 1;
                } else if(cakeVertical[position] == verticalMax) {
                    vertical+=1;
                }
            }

            // 결과 연산
            if(horizontal == 0) {
                System.out.println(vertical * N);
            } else if(vertical == 0) {
                System.out.println(horizontal * N);
            } else {
                System.out.println(vertical * horizontal);
            }
        }
    }
}
