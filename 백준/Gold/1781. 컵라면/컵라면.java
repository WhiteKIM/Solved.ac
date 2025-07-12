import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] deadLine = new int[200001];

        int N = Integer.parseInt(sc.nextLine());
        Queue<Problem> problemList = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int day = Integer.parseInt(input[0]);
            int ramen = Integer.parseInt(input[1]);

            problemList.add(new Problem(day, ramen));
        }

        long count = 0L;

        PriorityQueue<Integer> schedule = new PriorityQueue<>();

        while (!problemList.isEmpty()) {
            Problem p = problemList.poll();

            if(schedule.size() < p.day) {
                // 해당 날짜보다 스케쥴 값이 작으면 해당 일자에 배치된 작업이 없는 것
                schedule.add(p.ramenCount);
            } else {
                // 해당 일자에 배정된 작업 중, 다른 작업보다 더 적은 라면을 얻을 경우
                // 해당 작업 변경
                if(!schedule.isEmpty() && schedule.peek() < p.ramenCount) {
                    schedule.poll();
                    schedule.add(p.ramenCount);
                }
            }
        }

        for(int i : schedule) {
            count += (long) i;
        }

        System.out.println(count);
    }

    public static class Problem implements Comparable<Problem> {
        int day;
        int ramenCount;

        public Problem(int day, int ramenCount) {
            this.day = day;
            this.ramenCount = ramenCount;
        }

        @Override
        public int compareTo(Problem o) {
            int comp = Integer.compare(this.day, o.day);

            if(comp != 0) {
                return comp;
            }

            return Integer.compare(o.ramenCount, this.ramenCount);
        }
    }
}
