import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(sc.nextLine());

            String[] input = sc.nextLine().split(" ");
            PriorityQueue<Long> queue = new PriorityQueue<>();

            for(int j = 0; j < M; j++) {
                queue.add(Long.parseLong(input[j]));
            }

            long result = 0;

            while (queue.size() > 1) {
                long one = queue.poll();
                long two = queue.poll();

                queue.add(one + two);

                result += one + two;
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }
}
