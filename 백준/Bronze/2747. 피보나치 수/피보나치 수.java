import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] data = new int[46];
        data[0] = 0;
        data[1] = 1;

        for(int i = 2; i <= 45; i++) {
            data[i] = data[i-1] + data[i - 2];
        }

        int num = Integer.parseInt(sc.nextLine());
        System.out.println(data[num]);
    }
}