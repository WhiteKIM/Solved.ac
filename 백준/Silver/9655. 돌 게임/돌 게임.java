import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // N = 5일때
        // 상근이 1을 가져가면 창영은 1 또는 3
        // 상근이 3을 가져가면 창영은 1
        // 상근이 1이나 3을 가져가나 승리는 상근

        // N = 4일때
        // 상근이 1을 가져가면 창영이 3을 가져가고 승리
        // 상근이 3을 가져가면 창영이 1을 가져가고 승리

        // N은 N % 2 = 1일때 상근 승리
        // N은 N % 2 = 0 창영 승리

        if(N % 2 == 1) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
