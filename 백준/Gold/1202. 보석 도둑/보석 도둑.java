import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        List<Gem> gemList = new ArrayList<>();
        List<Integer> bagList = new ArrayList<>();
        boolean[] visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);

            gemList.add(new Gem(weight, value));
        }

        gemList.sort(Comparator.reverseOrder());

        long result = 0;

        for(int i = 0; i < M; i++) {
            int capacity = Integer.parseInt(sc.nextLine());
            bagList.add(capacity);
        }

        bagList.sort(Comparator.naturalOrder());
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int index = 0;

        for(int bag : bagList) {
            while (index < N && gemList.get(index).weight <= bag) {
                queue.add(gemList.get(index).value);
                index+=1;
            }

            if(!queue.isEmpty()) {
                result+= queue.poll();
            }
        }

        System.out.println(result);
    }

    public static class Gem implements Comparable<Gem> {
        int weight;
        int value;

        public Gem(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Gem o) {
            if(this.weight == o.weight) {
                return  o.value - this.value;
            }

            return o.weight - this.weight;
        }
    }
}
