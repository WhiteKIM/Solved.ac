import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static int point = 0;
    public static int target = 0;
    public static int winning = 0;
    public static int lossing = 0;
    public static Set<String> easy = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        input = sc.nextLine().split(" ");
        winning = Integer.parseInt(input[0]);
        lossing = Integer.parseInt(input[1]);
        target = Integer.parseInt(input[2]);

        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");
            
            if(input[1].equals("W")) {
                easy.add(input[0]);
            }
        }

        boolean check = false;
        for(int i = 0; i < N; i++) {
            solved(sc.nextLine());
            if(point >= target) {
                check = true;
            }
        }

        if(check) {
            System.out.println("I AM NOT IRONMAN!!");
        } else {
            System.out.println("I AM IRONMAN!!");
        }
    }

    public static void solved(String name) {
        if(easy.contains(name)) {
            calcPoint("W");
        } else {
            calcPoint("L");
        }
    }

    public static void calcPoint(String status) {
        if(status.equals("L")) {
            if(point - lossing < 0)
                point = 0;
            else
                point -= lossing;
        } else {
            point += winning;
        }
    }
}
