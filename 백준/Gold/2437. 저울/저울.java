import java.util.*;

public class Main {
    public static List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[] input = sc.nextLine().split(" ");

        for(String num : input) {
            int parsedNum  = Integer.parseInt(num);
            numbers.add(parsedNum);
        }

        Collections.sort(numbers);

        // 측정가능한 무게치 검색
        int weight = 1;

        for(int i = 0; i < N; i++) {
            if(weight < numbers.get(i))
                break;

            weight += numbers.get(i);
        }

        // weight는 1부터 측정가능한 범위까지 등록됨
        System.out.println(weight);
    }
}
