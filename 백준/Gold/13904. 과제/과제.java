import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());

        List<HomeWork> homeWorkList = new ArrayList<>();
        boolean[] visited = new boolean[1001];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int day = Integer.parseInt(input[0]);
            int point = Integer.parseInt(input[1]);

            homeWorkList.add(new HomeWork(day, point));
        }

        int result = 0;

        Collections.sort(homeWorkList);

        for(int i = 0; i < N; i++) {
            // 과제는 과제점수가 높고
            // 과제마감일에 최대한 가깝게 배치

            HomeWork work = homeWorkList.get(i);

            for(int day = work.day; day > 0; day--) {
                if(!visited[day]) {
                    result+=work.point;
                    visited[day] = true;
                    break;
                }
            }
        }

        System.out.println(result);
    }


    public static class HomeWork implements Comparable<HomeWork> {
        int day;
        int point;

        public HomeWork(int day, int point) {
            this.day = day;
            this.point = point;
        }

        @Override
        public int compareTo(HomeWork o) {
            int comp = Integer.compare(o.point, this.point);

            if(comp != 0) {
                return comp;
            }

            return Integer.compare(this.day, o.day);
        }
    }
}
