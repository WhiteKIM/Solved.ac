import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[][] triangle = new int[N][N];
        int index = 1;

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < index; j++) {
                triangle[i][j] = Integer.parseInt(input[j]);
            }

            index+=1;
        }

        int max = triangle[0][0];

        for(int i = 1; i < N; i++) {

            for(int j = 0; j < i+1; j++) {
                if(j == i) {    // 가장 끝의 값일때는 항상 이전 층의 우측값이 선택된다.
                    triangle[i][j] += triangle[i - 1][j - 1];
                } else if(j == 0) {// 첫번째 값의 위치는 항상 이전층의 첫번째값이 선택된다.
                    triangle[i][j] += triangle[i - 1][0];
                }else {
                    triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            max = Math.max(max, triangle[N-1][i]);
        }

        System.out.println(max);
    }
}
