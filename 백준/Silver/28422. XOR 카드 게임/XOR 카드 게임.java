import java.util.Scanner;

public class Main {
    public static int[] data;
    public static int[] card;
    public static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        data = new int[N+1];
        card = new int[N+1];
        String[] input = sc.nextLine().split(" ");

        for(int i = 1; i <= N; i++) {
            card[i] = Integer.parseInt(input[i-1]);
        }

        solved();
        System.out.println(data[N]);
     }

    public static void solved() {
        if(N < 2)
            return;

        data[2] = count(card[1] ^ card[2]);
        data[3] = count(card[1] ^ card[2] ^ card[3]);
        data[4] = data[2] + count(card[3] ^ card[4]);

        for(int i = 5; i <= N; i++) {
            int n1 = count(card[i-1] ^ card[i]);
            int n2 = count(card[i-2] ^ card[i-1] ^ card[i]);
            data[i] = Math.max(data[i-2]+n1, data[i-3]+n2);
        }
    }

    public static int count(int N) {
        String str = Integer.toBinaryString(N);
        int cnt = 0;

        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '1')
                cnt+=1;
        }

        return cnt;
    }
}
