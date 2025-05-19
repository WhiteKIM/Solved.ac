import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        List<String> wordList = new ArrayList<>();
        Map<Character, Integer> comb = new HashMap<>();
        int[] numbers = new int[26];

        for(int i = 0; i < N; i++) {
            wordList.add(sc.nextLine());
        }

        for(int i = 0; i < 26; i++) {
            comb.put((char) ('A' + i), 0);
        }

        // 알파벳으 우선순위를 계산한다.
        for(String str : wordList) {

            for(int i = 0; i < str.length(); i++) {
                int len = str.length() - i - 1;
                char ch = str.charAt(i);
                int sum = comb.get(ch) + (int) Math.pow(10, len);

                comb.put(ch, sum);
            }
        }

        // 알파벳 높은 우선순위부터 값 할당
        int val = 9;

        for(int i = 0; i < 26; i++) {
            char ch = '#';
            int max = 0;

            for(int j = 0; j < 26; j++) {
                char index = (char) ('A' + j);

                if(comb.containsKey(index) && max < comb.get(index)) {
                    max = comb.get(index);
                    ch = index;
                }
            }

            if(ch != '#') {
                numbers[ch - 'A'] = val;
                val--;
                comb.remove(ch);
            }
        }

        int result = 0;

        for(String str : wordList) {
            int value = 0;

            for(int i = 0; i < str.length(); i++) {
                int len = str.length() - i - 1;
                int index = str.charAt(i) - 'A';
                value += (int) (numbers[index] * Math.pow(10, len));
            }

            result += value;
        }

        System.out.println(result);
    }
}
