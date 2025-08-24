import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        List<Flower> flowerList = new ArrayList<>();

        // 3월1일부터 11월30일까지 꽃이 항상 하나 이상 피어있도록 구성
        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int startM = Integer.parseInt(input[0]);
            int startD = Integer.parseInt(input[1]);
            int endM = Integer.parseInt(input[2]);
            int endD = Integer.parseInt(input[3]);

            flowerList.add(new Flower(startM, startD, endM, endD));
        }

        flowerList.sort(Comparator.naturalOrder());

        int startDate = 301;
        int endDate = 1201;
        int index = 0;
        int count = 0;

        // 11월 30일 도달 가능?
        while(startDate < endDate && index < N)  {
            int maxDate = 0;
            int idx = -1;

            for(int i = index; i < N; i++) {
                Flower f = flowerList.get(i);

                // 선택가능한 항목에서 가장 늦게 지는 꽃을 선택한다.
                if(f.startDate <= startDate && startDate <= f.endDate) {
                    if(maxDate < f.endDate) {
                        maxDate = f.endDate;
                        idx = i;
                    }
                }
            }

            if(maxDate != 0) {
                startDate = maxDate;
                count+=1;
                index =  idx + 1;
            } else {
                // 현재 사용가능한 항목 X
                index += 1;
            }
        }

        if(startDate < endDate) {
            System.out.println(0);
        } else {
            System.out.println(count);
        }
    }

    public static class Flower implements Comparable<Flower>{
        int startDate;
        int endDate;

        public Flower(int startMonth, int startDay, int endMonth, int endDay) {
            this.startDate = startMonth * 100 + startDay;
            this.endDate = endMonth * 100 + endDay;
        }

        @Override
        public int compareTo(Flower o) {
            int startM = Integer.compare(this.startDate, o.startDate);

            if(startM != 0)
                return startM;

            return Integer.compare(this.endDate, o.endDate) * -1;
        }
    }
}
