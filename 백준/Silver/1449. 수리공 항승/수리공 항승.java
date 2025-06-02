import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);
        int count = 0;

        boolean[] waterList = new boolean[1001];
        input = sc.nextLine().split(" ");

        for(String str : input) {
            int location = Integer.parseInt(str);
            waterList[location] = true;
        }

        // 시작위치부터 테이프를 한번 칠한다.
        for(int i = 1; i < 1001;) {
            if(waterList[i]) {
                count+=1;
                i += L;
            } else {
                i++;
            }
        }

        System.out.println(count);
    }
}
