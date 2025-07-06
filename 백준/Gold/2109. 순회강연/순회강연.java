import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        boolean[] schedule = new boolean[10001];
        List<Lecture> lectures = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int money = Integer.parseInt(input[0]);
            int day = Integer.parseInt(input[1]);

            lectures.add(new Lecture(day, money));
        }

        Collections.sort(lectures);
        int income = 0;

        for(int i = 0; i < N; i++) {
            Lecture lecture = lectures.get(i);

            for(int j = lecture.day; j > 0; j--) {
                if(!schedule[j]) {
                    schedule[j] = true;
                    income += lecture.money;
                    break;
                }
            }
        }

        System.out.println(income);
    }
    
    public static class Lecture implements Comparable<Lecture> {
        int day;
        int money;

        public Lecture(int day, int money) {
            this.day = day;
            this.money = money;
        }

        @Override
        public int compareTo(Lecture o) {
            // 돈으로 내림차순
            int comp = Integer.compare(o.money, this.money);
            
            if(comp != 0)
                return comp;
            
            // 일자로 오름차순
            return Integer.compare(this.day, o.day);
        }
    }
}
