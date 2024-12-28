import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        int N = Integer.parseInt(sc.nextLine());
        List<A> removeList = new ArrayList<>();
        int[] point = new int[S.length() + 1];

        for(int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            removeList.add(new A(input[0], Integer.parseInt(input[1])));
        }

        for(int i = 0; i < S.length(); i++) {
            // 주어진 문제에서 문자열을 1개 지우거나, 포함된 문자열을 제거하거나
            // 둘 중 하나의 연산을 취해야 한다.
            // 문자 하나 제거
            point[i+1] = Math.max(point[i+1], point[i] + 1);
            
            // 부분문자열 제거
            for(A a : removeList) {
                if(S.startsWith(a.str, i)) {
                    point[i + a.str.length()] = Math.max(point[i + a.str.length()], point[i] + a.point);
                }
            }
        }

        System.out.println(point[S.length()]);
    }

    public static class A {
        String str;
        int point;

        public A(String str, int point) {
            this.str = str;
            this.point = point;
        }
    }
}
