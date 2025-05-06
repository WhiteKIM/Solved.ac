import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static char[] numbers;
    public static int number;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        numbers = input[0].toCharArray();
        number = -1;

        int K = Integer.parseInt(input[1]);
        int comp = Integer.parseInt(input[0]);

        solved(comp, K, numbers);

        if(number == -1) {
            System.out.println(-1);
        } else {
            System.out.println(number);
        }
    }

    public static void solved(int val, int K, char[] numbers) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[K+1][1000001];
        queue.add(new int[]{0, val});
        visited[0][val] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int cnt = arr[0];
            int num = arr[1];

            if(cnt == K) {
                number = Math.max(number, num);
                continue;
            }

            if(K < cnt)
                continue;

            for(int i = 0; i < numbers.length; i++) {
                for(int j = 0; j < numbers.length; j++) {
                    if(i == j)
                        continue;

                    char[] temp = String.valueOf(num).toCharArray();
                    swap(i, j, temp);
                    int value = Integer.parseInt(String.valueOf(temp));

                    if(!visited[cnt + 1][value] && temp[0] != '0') {
                        visited[cnt + 1][value] = true;
                        queue.add(new int[]{cnt + 1, value});
                    }
                }
            }
        }
    }

    public static char[] swap(int prev, int next, char[] numbers) {
        char temp = numbers[prev];
        numbers[prev] = numbers[next];
        numbers[next] = temp;

        return numbers;
    }
}
