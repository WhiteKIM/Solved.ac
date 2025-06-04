import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] line = new int[N];
        int[] result = new int[N];

        String[] input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(input[i]);
        }

        for(int i = 0; i < N; i++) {
            int count = 0;

            // 배열을 탐색하면서 왼쪽에 자신보다 큰 사람이 위치할 수
            // 있는 자리의 개수를 카운트하면서 위치를 탐색한다.
            for(int j = 0; j < N; j++) {
                if(count == line[i] && result[j] == 0) {
                    result[j] = i + 1;
                    break;
                } else if(result[j] == 0) {
                    count+=1;
                }
            }
        }

        for(int num  : result) {
            System.out.print(num + " ");
        }
    }
}
