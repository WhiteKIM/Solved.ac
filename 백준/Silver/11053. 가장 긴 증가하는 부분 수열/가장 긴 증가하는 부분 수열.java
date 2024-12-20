import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] arr = new int[N];
        int[] result = new int[N];

        String[] input = sc.nextLine().split(" ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        
        int num = 0;
        result[0] = 1;
        for(int i = 1; i < N; i++) {
            result[i] = 1;
            
            for(int j = 0; j < i; j++) {
                // 이전 위치의 값 중에서 현재 위치값보다 작은 경우 탐색
                if(arr[i] > arr[j] && result[i] < result[j]+1) {
                    result[i] = result[j]+1;
                }
            }
        }

        for(int i : result)
            num = Math.max(i, num);

        System.out.println(num);
    }
}
