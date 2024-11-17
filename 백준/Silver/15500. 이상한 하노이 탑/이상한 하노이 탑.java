import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> first = new Stack<>();
        Stack<Integer> second = new Stack<>();
        Stack<Integer> third = new Stack<>();
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < N; i++) { // 첫번째 봉 세팅
            first.push(sc.nextInt());
        }

        int count = 0;
        int index = N;

        while (index > 0) {
            while (!first.isEmpty()) {
                int top = first.peek();

                if(top == index) {
                    sb.append(1).append(" ").append(3).append("\n");
                    count+=1;
                    index-=1;
                    third.push(first.pop());
                } else {
                    sb.append(1).append(" ").append(2).append("\n");
                    count+=1;
                    second.push(first.pop());
                }
            }

            while (!second.isEmpty()) {
                int top = second.peek();

                if(top == index) {
                    sb.append(2).append(" ").append(3).append("\n");
                    count+=1;
                    index-=1;
                    third.push(second.pop());
                } else {
                    sb.append(2).append(" ").append(1).append("\n");
                    count+=1;
                    first.push(second.pop());
                }
            }
        }

        System.out.println(count);
        System.out.println(sb.toString());
    }
}
