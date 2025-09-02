import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        long total = 0;
        List<Village> villageList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int index = Integer.parseInt(input[0]);
            long people = Long.parseLong(input[1]);

            villageList.add(new Village(index, people));
            total += people;
        }

        villageList.sort(Comparator.naturalOrder());

        // 전체 인구수의 절반되는 지점에 설치해야 결국 거리의 합이
        // 최소를 가질 수 있다.
        long sum = 0;

        for(Village v : villageList) {
            sum += v.people;

            if((total + 1) / 2 <= sum) {
                System.out.println(v.index);
                break;
            }
        }
    }

    public static class Village implements Comparable<Village> {
        int index;
        long people;

        public Village(int index, long people) {
            this.index = index;
            this.people = people;
        }

        @Override
        public int compareTo(Village o) {
            return Integer.compare(index, o.index);
        }
    }
}
