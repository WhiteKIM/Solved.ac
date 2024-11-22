import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static boolean[] digit = new boolean[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] input = br.readLine().toCharArray();
        long N = Long.parseLong(String.valueOf(input));

        for(char ch : input) {
            int num = (int)ch - '0';
            digit[num] = true;
        }

        System.out.println(solved(N));
        
        br.close();
    }

    public static long solved(long N) {
        long result = N;
        boolean check = true;

        for(int j = 1; j <= 9; j++) {
            if(digit[j]) {
                if(result % j != 0) {
                    check = false;
                    break;
                }
            }
        }

        if(check) {
            return result;
        }

        result = N * 10;
        for(int i = 0; i <= 9; i++) {
            check = true;
            long num = result + i;

            for(int j = 1; j <= 9; j++) {
                if(digit[j]) {
                    if(num % j != 0) {
                        check = false;
                        break;
                    }
                }
            }

            if(check) {
                return num;
            }
        }

        result = N * 100;
        for(int i = 0; i <= 99; i++) {
            check = true;
            long num = result + i;

            for(int j = 1; j <= 9; j++) {
                if(digit[j]) {
                    if(num % j != 0) {
                        check = false;
                        break;
                    }
                }
            }

            if(check) {
                return num;
            }
        }

        result = N * 1000;
        for(int i = 0; i <= 999; i++) {
            check = true;
            long num = result + i;

            for(int j = 1; j <= 9; j++) {
                if(digit[j]) {
                    if(num % j != 0) {
                        check = false;
                        break;
                    }
                }
            }

            if(check) {
                return num;
            }
        }

        result = N * 10000;
        for(int i = 0; i <= 9999; i++) {
            check = true;
            long num = result + i;

            for(int j = 1; j <= 9; j++) {
                if(digit[j]) {
                    if(num % j != 0) {
                        check = false;
                        break;
                    }
                }
            }

            if(check) {
                return num;
            }
        }

        return -1;//여기까지 오면 안되요.
    }
}
