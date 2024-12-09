import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int N = Integer.parseInt(sc.nextLine());
            if(N == 0) {
                break;
            }

            char[] input = sc.nextLine().toCharArray();
            int[] checked = new int[128];
            int cnt = 0;
            int left = -1;
            int right = -1;
            int max = 0;

            while (left <= right) {
                if(cnt < N) {   // 사용된적 없는 값
                    right++;
                    checked[input[right]]+=1;
                    if(checked[input[right]] == 1)
                        cnt++;

                } else if(cnt == N && checked[input[right+1]] > 0) {  // 다음 값 동일여부
                    right++;
                    checked[input[right]]+=1;
                } else {    // 더 이상 사용할 수 있는 키가 없음
                    left++;
                    checked[input[left]]--;
                    if (checked[input[left]] == 0) {    //  사용되지 않는 문자
                        cnt--;
                    }
                }

                max = Math.max(max, right - left);

                if(right == input.length - 1) { // 끝에 도달
                    break;
                }
            }

            System.out.println(max);
        }
    }
}
