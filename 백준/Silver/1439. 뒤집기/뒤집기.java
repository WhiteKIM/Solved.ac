import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int count = 0;

        // 모든 수를 0으로 바꾸거나 1로 바꿔야 한다.
        // 아이디어 : 해당 문자열에서 동일 숫자로만 이루어진 소그룹을 찾고
        // 0, 1 둘 중 그룹의 수가 더 작은 경우가 변경횟수라고 생각한다.

        int one = 0;
        int zero = 0;

        for(int i = 1; i < input.length(); i++) {
            char prev = input.charAt(i - 1);
            char now = input.charAt(i);

            if(prev != now) {
                if(prev == '0') {
                    zero+=1;
                } else {
                    one+=1;
                }
            }
        }
        
        // 마지막숫자 그룹 판별
        if(input.charAt(input.length() - 1) == '0') {
            zero += 1;
        } else {
            one += 1;
        }

        count = Math.min(one, zero);

        System.out.println(count);
    }
}
