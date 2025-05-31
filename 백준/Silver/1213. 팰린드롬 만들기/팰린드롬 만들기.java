import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();
        int[] alpabet = new int[26];

        int length = input.length;

        for(int i = 0; i < length; i++) {
            int index=  input[i] - 'A';
            alpabet[index]+=1;
        }

        char[] result = new char[length];
        int start = 0;
        int last = length - 1;

        // 홀수라면 한 알파벳만 홀수가 가능하고, 나머지는 모두 짝수
        if(!isPalindrome(alpabet, length)) {
            System.out.println("I'm Sorry Hansoo");

            return;
        }


        while (start < last) {
            int index = -1;

            for(int i = 0; i < 26; i++) {
                if(alpabet[i] >= 2) {
                    index = i;

                    break;
                }
            }

            // 가장 가까운 문자들부터 앞쪽에 배치
            if(index != -1) {
                result[start] = (char) ('A' + index);
                result[last] = (char) ('A' + index);

                start +=1;
                last -= 1;
                alpabet[index] -= 2;
            }
        }

        if(length % 2 == 1) {
            for(int i = 0; i < 26; i++) {
                if(alpabet[i] > 0) {
                    result[start] = (char) ('A' + i);
                }
            }
        }

        System.out.println(String.valueOf(result));
    }

    public static boolean isPalindrome(int[] palindrome, int length) {
        if(length % 2 == 0) {
            for(int i = 0; i < 26; i++) {
                if(palindrome[i] % 2 != 0) {
                    return false;
                }
            }
        } else {
            // 홀수카운트
            int count = 0;

            for(int i = 0; i < 26; i++) {
                if(palindrome[i] % 2 != 0) {
                    count += 1;
                }
            }

            if(count > 1) {
                return false;
            }
        }

        return true;
    }
}
