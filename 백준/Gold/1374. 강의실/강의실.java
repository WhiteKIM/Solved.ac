import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        PriorityQueue<Lecture> lectures = new PriorityQueue<>();
        PriorityQueue<Integer> classRoom = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            int no = Integer.parseInt(input[0]);
            int start = Integer.parseInt(input[1]);
            int end = Integer.parseInt(input[2]);

            lectures.add(new Lecture(no, start, end));
        }
        
        while (!lectures.isEmpty()) {
            Lecture lecture = lectures.poll();

            // classRoom은 강의실의 종료시점을 저장한다.
            // 앞에 오는 강의실부터 비교하면서, 해당 강의실이 사용가능한지 판단한다.
            if(!classRoom.isEmpty() && classRoom.peek() <= lecture.start) {
                // 강의실 재사용을 위해서 큐에서 제거
                classRoom.poll();
            }

            // 새로운 종료시점을 기록한다.
            classRoom.add(lecture.end);
        }

        System.out.println(classRoom.size());
    }

    public static class Lecture implements Comparable<Lecture> {
        int No;
        int start;
        int end;

        public Lecture(int no, int start, int end) {
            No = no;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            int startComp = Integer.compare(this.start, o.start);

            if(startComp != 0) {
                return startComp;
            }

            return Integer.compare(this.end, o.end);
        }
    }
}
