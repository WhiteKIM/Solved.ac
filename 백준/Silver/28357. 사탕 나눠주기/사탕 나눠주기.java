import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        long N = Long.parseLong(input[0]);
        long candy = Long.parseLong(input[1]);

        long min = 0;
        long max = Long.MIN_VALUE;

        input = br.readLine().split(" ");
        ArrayList<Long> data = new ArrayList<>();

        for(String str : input) {
            long num = Long.parseLong(str);
            data.add(num);
            max = Math.max(max, num);
        }

        while (min < max) {
            long target = (min + max)/2;
            int result = solved(candy, target, data);

            if(result == 0)
                break;

            if(result > 0) {
                max = target;
            } else {
                min = target+1;
            }
        }

        System.out.println((min + max)/2);
        br.close();
    }

    public static int solved(long candy, long target, ArrayList<Long> data) {
        for(Long i : data) {
            if(i >= target) {
                candy -= (i - target);
            }

            if(candy < 0) // 탐색을 중지
                return -1;
        }

        if(candy > 0)   // 캔디가 남았다.
            return 1;
        else if(candy < 0)  // 캔디가 모자른다.
            return -1;

        return 0;
    }
}
