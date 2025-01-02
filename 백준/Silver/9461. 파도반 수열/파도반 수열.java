import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        long[] data = new long[101];
        data[1] = 1;
        data[2] = 1;
        data[3] = 1;
        data[4] = 2;
        data[5] = 2;

        for(int i = 6; i <= 100; i++) {
            data[i] = data[i - 2] + data[i - 3];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            int num = Integer.parseInt(sc.nextLine());
            sb.append(data[num]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
