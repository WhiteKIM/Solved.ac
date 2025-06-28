import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int me = Integer.parseInt(sc.nextLine());
        int count = 0;

        for(int i = 0; i < N - 1; i++) {
            queue.add(Integer.parseInt(sc.nextLine()));
        }

        while (!queue.isEmpty()) {
            if(me > queue.peek()) {
                break;
            }

            int you = queue.poll();
            count+=1;
            me+=1;
            you -= 1;

            queue.add(you);
        }

        System.out.println(count);
    }
}
