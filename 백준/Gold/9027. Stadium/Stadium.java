import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 누적합으론 못 풀겠다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());    // 마을 개수
            ArrayList<Long> vill = new ArrayList<>();   // 마을
            ArrayList<Long> fan = new ArrayList<>();    // 팬

           int index = 0;
           long total = 0L;

           String[] input = br.readLine().split(" ");
           for(int i = 0; i < N; i++) {
               vill.add(Long.parseLong(input[i]));
           }

            input = br.readLine().split(" ");
            for(int i = 0; i < N; i++) {
                long num = Long.parseLong(input[i]);
                fan.add(num);
                total += num;
            }

            Long sum = 0L;
            for(int i = 0; i < N; i++) {
                sum += fan.get(i);

                if(sum >= (total + 1) / 2) {
                    index = i;
                    break;
                }
            }

            sb.append(vill.get(index)).append("\n");
        }

        System.out.println(sb.toString());
    }
}
