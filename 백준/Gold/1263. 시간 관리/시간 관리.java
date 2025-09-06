import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        List<Job> jobList = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int hour = Integer.parseInt(input[0]);
            int last = Integer.parseInt(input[1]);

            jobList.add(new Job(hour, last));
        }

        jobList.sort(Comparator.naturalOrder());

        int leastTime = Math.max(jobList.get(0).lastTime - jobList.get(0).hour, 0);
        boolean check = true;
        int minTime = leastTime;

        // 모든 작업을 실행가능한지

        for(int t = leastTime; t >= 0; t--) {
            int time = t;
            boolean ch = true;

            for(Job j : jobList) {
                time += j.hour;

                if(time > j.lastTime) {
                    // 불가능한 작업 존재
                    ch = false;
                    break;
                }
            }

            // 항상 마지막 결과를 기록
            check = ch;

            if(ch) {
                minTime = t;
                break;
            }
        }

        if(check) {
            System.out.println(minTime);
        } else {
            System.out.println(-1);
        }
    }

    public static class Job implements Comparable<Job> {
        int hour;
        int lastTime;

        public Job(int hour, int lastTime) {
            this.hour = hour;
            this.lastTime = lastTime;
        }

        @Override
        public int compareTo(Job o) {
            int c1 = Integer.compare(this.lastTime, o.lastTime);

            if(c1 != 0)
                return c1;

            return Integer.compare(o.hour, this.hour);
        }
    }
}
