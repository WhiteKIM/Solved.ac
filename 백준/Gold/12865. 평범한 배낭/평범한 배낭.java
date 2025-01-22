import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int[][] bag = new int[N+1][K+1];
        Item[] itemList = new Item[N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");
            int w = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);

            itemList[i] = new Item(w, v);
        }


        // 1차원 배열에서는 같은 물건을 중복으로 넣을 가능성이 존재
        // 넣었던 물건을 재사용하지 않도록 해야한다.
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                if(j - itemList[i-1].weigth >= 0) {
                    bag[i][j] = Math.max(bag[i-1][j - itemList[i-1].weigth] + itemList[i-1].value, bag[i - 1][j]);
                } else {
                    bag[i][j] = bag[i - 1][j];
                }
            }
        }

        System.out.println(bag[N][K]);
    }

    public static class Item {
        int weigth;
        int value;

        public Item(int weigth, int value) {
            this.weigth = weigth;
            this.value = value;
        }
    }
}