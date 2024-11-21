import java.io.*;
import java.util.*;

public class Main {
    public static int N = 0;
    public static int M = 0;
    public static Set<Pair> path = new HashSet<>();
    public static boolean check = true;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");

        for(int i = 0; i < M; i++) { // 100,000
            int len = Integer.parseInt(input[i]);
            String[] inputWater = br.readLine().split(" ");
            int size = 0;
            Set<Pair> temp = new HashSet<>();

            for(int j = 0; j < len; j+=2) {    // 10
                int x = Integer.parseInt(inputWater[j]);
                int y = Integer.parseInt(inputWater[j + 1]);

                size+=solved(x, y, i + 1, temp);
            }

            path = temp;

            if(size == 0) {
                check = false;
                break;
            }
        }

        if(check) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }

        br.close();
        bw.close();
    }

    public static int solved(int x, int y, int index, Set<Pair> temp) {
        if(index == 1) {
            path.add(new Pair(x, y));   // 이전 노드
            temp.add(new Pair(x, y));   // 추가된 이웃 노드
        } else {
            for(Pair node : path) {
                if((node.x <= x && x <= node.y) || (node.x <= y && y <= node.y) || (x <= node.x && node.x <= y) || (x <= node.y && node.y <= y)) {
                    //System.out.println("index : "+index+" X : "+x+" Y : "+y);
                    temp.add(new Pair(x, y));
                }
            }
        }

        if(temp.size() == 0) {
            return 0;
        }

        return 1;
    }

    public static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public String toString() {
            return ("x : " +x+" y : "+y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (x != pair.x) return false;
            return y == pair.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}