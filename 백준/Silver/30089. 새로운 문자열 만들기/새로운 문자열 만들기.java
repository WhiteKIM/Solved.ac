import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            int index = 0;

            for(int j = 0; j < input.length(); j++) {
                String sub = input.substring(j);
                String reverse = new StringBuilder(sub).reverse().toString();

                if(sub.contains(reverse)) {
                    if(j == 0) {
                        break;
                    }
                    index = j;
                    break;
                }
            }

            if(index > 0) {
                String str = new StringBuilder(input.substring(0, index)).reverse().toString();
                sb.append(input).append(str);
            } else {
                sb.append(input);
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
