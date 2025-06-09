import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        input = sc.nextLine().split(" ");
        int[] sequence = new int[K];
        Map<Integer, Queue<Integer>> data = new HashMap<>();
        Set<Integer> result = new HashSet<>();

        for(int i = 0; i < K; i++) {
            int num = Integer.parseInt(input[i]);
            sequence[i] = num;

            if(data.containsKey(num)) {
                data.get(num).add(i);
            } else {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                data.put(num, queue);
            }
        }

        int count = 0;  // 플러그에서 뽑힌 개수
        int plug = 0;   // 현재 플러그에 꼽힌 기기 개수

        // 값을 하나씩 넣어보면서 체크
        for(int i = 0; i < K; i++) {
            // 들어온 순서대로 값을 가져와서 판단해보자
            int val = sequence[i];

            // 일단 사용처리를 하나 수행
            data.get(val).poll();

            // 따로 볼 필요 없음
            if(result.contains(val))
                continue;

            if(result.size() < N) {
                // 이전에 꼽은 적 없는 플러그인
                if(!result.contains(val)) {
                    result.add(val);
                    plug+=1;
                }
            } else {
                int removeKey = -1;
                int dist = -1;

                for(int key : result) {
                    Queue<Integer> queue = data.get(key);

                    // 더 이상 사용하지 않는 플러그
                    if(queue.isEmpty()) {
                        removeKey = key;
                        break;
                    }

                    // 최근에 사용하지 않을 플러그 찾아서 제거
                    int last = queue.peek();
                    if(dist < last) {
                        dist = last;
                        removeKey = key;
                    }
                }

                result.remove(removeKey);
                result.add(val);
                count += 1;
            }
        }

        System.out.println(count);
    }
}
