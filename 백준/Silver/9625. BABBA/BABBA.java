import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        Data[] result = new Data[46];
        result[0] = new Data(1, 0);
        result[1] = new Data(0, 1);

        for(int i = 2; i <= 45; i++) {
            result[i] = new Data(result[i-1].A + result[i-2].A, result[i-1].B + result[i-2].B);
        }

        System.out.println(result[N].toString());
    }
    
    public static class Data {
        int A = 0;
        int B = 0;

        public Data(int A, int B) {
            this.A = A;
            this.B = B;
        }

        @Override
        public String toString() {
            return A+" "+B;
        }
    }
}
