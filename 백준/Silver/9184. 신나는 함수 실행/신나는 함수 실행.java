import java.util.Scanner;

public class Main {
    public static int[][][] data = new int[51][51][51];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String[] input = sc.nextLine().split(" ");

            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);

            if(a == -1 && b == -1 && c == -1)
                break;

            String out = "w("+a+", "+b+", "+c+") = "+recursive(a, b, c);
            System.out.println(out);
        }
    }

    public static int recursive(int a, int b, int c) {
        if((a >= 0 && b >= 0 && c >= 0) && data[a][b][c] != 0) {
            return data[a][b][c];
        }

        if(a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if(a > 20 || b > 20 || c > 20) {
            data[20][20][20] = recursive(20, 20, 20);
            return data[20][20][20];
        }

        if(a < b && b < c) {
            data[a][b][c] = recursive(a, b, c-1) + recursive(a, b-1, c-1) - recursive(a, b-1, c);
            return data[a][b][c];
        }

        data[a][b][c] = recursive(a-1, b, c) + recursive(a-1, b-1, c) + recursive(a-1, b, c-1) - recursive(a-1, b - 1, c-1);

        return data[a][b][c];
    }
}
