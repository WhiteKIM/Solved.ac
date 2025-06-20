import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] cranes = new int[N];
        int[] boxes;

        String[] input = sc.nextLine().split(" ");

        for(int i = 0; i < N; i++) {
            cranes[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(cranes);

        int M = Integer.parseInt(sc.nextLine());
        boxes = new int[M];
        boolean[] visited = new boolean[M];
        int[] cranesPosition = new int[N];
        input = sc.nextLine().split(" ");

        for(int i = 0; i < M; i++) {
            boxes[i] = Integer.parseInt(input[i]);
        }

        Arrays.fill(cranesPosition, M - 1);
        Arrays.sort(boxes);

        // 로직 설명
        // 모든 크레인은 항상 자신이 옮기는 최대 무게와 가장 가까운 박스를
        // 선택해서 선적한다.

        int time = 0;
        int boxCount = 0;

        if(cranes[N - 1] < boxes[M - 1]) {
            // 가장 큰 박스의 무게가 크레인이
            // 옮길 수 있는 최대 중량을 초과
            System.out.println(-1);
        } else {
            while (M > 0) {
                for(int i = N - 1; i >= 0; i--) {
                    for(int j = cranesPosition[i]; j >= 0; j--) {
                        if(visited[j]) {
                            continue;
                        }

                        if(boxes[j] > cranes[i]) {
                            cranesPosition[i]--;
                            continue;
                        }

                        if(boxes[j] <= cranes[i]) {
                            visited[j] = true;
                            M--;
                            break;
                        }
                    }
                }

                time+=1;
            }

            System.out.println(time);
        }
    }
}
