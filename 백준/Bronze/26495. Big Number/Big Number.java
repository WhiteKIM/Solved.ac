import java.util.Scanner;

public class Main {
    public static String[] digits = {
            """
            0000
            0  0
            0  0
            0  0
            0000
            """,
            """
               1
               1
               1
               1
               1
            """,
            """
            2222
               2
            2222
            2
            2222
            """,
            """
            3333
               3
            3333
               3
            3333
            """,
            """
            4  4
            4  4
            4444
               4
               4
            """,
            """
            5555
            5
            5555
               5
            5555
            """,
            """
            6666
            6
            6666
            6  6
            6666
            """,
            """
            7777
               7
               7
               7
               7
            """,
            """
            8888
            8  8
            8888
            8  8
            8888
            """,
            """
            9999
            9  9
            9999
               9
               9
            """
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length ; i++) {
            int c = (int)input[i] - '0';
            sb.append(digits[c]).append("\n");;
        }

        System.out.print(sb.toString());
    }
}
