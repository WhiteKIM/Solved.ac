import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(sc.readLine());
        List<Long> milkArray = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            milkArray.add(Long.parseLong(sc.readLine()));
        }

        milkArray.sort(Comparator.reverseOrder());

        Queue<Long> milkList = new LinkedList<>(milkArray);

        long sum = 0;

        while (!milkList.isEmpty()) {
            if(milkList.size() >= 3) {
                for(int i = 0; i < 3; i++) {
                    long milk = milkList.poll();

                    if(i != 2) {
                        sum += milk;
                    }
                }
            } else {
                while (!milkList.isEmpty()) {
                    sum += milkList.poll();
                }
            }
        }

        System.out.println(sum);
        sc.close();
    }
}
