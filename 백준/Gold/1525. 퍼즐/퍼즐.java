import java.util.*;

public class Main {
    public static char[] cube;
    public static Map<String, Integer> visited = new HashMap<>();
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 3; i++) {
            String[] input = sc.nextLine().split(" ");

            for(int j = 0; j < 3; j++) {
                sb.append(input[j]);
            }
        }

        cube = sb.toString().toCharArray();

        if(solved()) {
            System.out.println(visited.get("123456780"));
        } else {
            System.out.println(-1);
        }
    }

    public static boolean solved() {
        Queue<String> queue = new LinkedList<>();
        queue.add(String.valueOf(cube));
        visited.put(String.valueOf(cube), 0);

        while (!queue.isEmpty()) {
            char[] str = queue.poll().toCharArray();
            int[] arr = getZero(str);
            int x = arr[0];
            int y = arr[1];
            int index = (x * 3) + y;

            if(String.valueOf(str).equals("123456780")) {
                return true;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0 <= nx && nx < 3 && 0 <= ny && ny < 3) {
                    int nindex = (nx * 3) + ny;
                    char[] newStr = str.clone();
                    char ch = newStr[nindex];
                    newStr[nindex] = newStr[index];
                    newStr[index] = ch;

                    if(!visited.containsKey(String.valueOf(newStr))) {
                        visited.put(String.valueOf(newStr), visited.get(String.valueOf(str))+ 1);
                        queue.add(String.valueOf(newStr));
                    }
                }
            }
        }

        return false;
    }

    public static int[] getZero(char[] str) {
        int x = 0;
        int y = 0;

        for(int i = 0; i < str.length; i++) {
            if(str[i] == '0') {
                x = i / 3;
                y = i % 3;
            }
        }

        return new int[]{x, y};
    }
}
