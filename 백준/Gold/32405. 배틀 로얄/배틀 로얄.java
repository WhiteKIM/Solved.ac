import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;

public class Main {
    public static int totalAttack = 0;
    public static int[] repeat;
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(sc.readLine());
        repeat = new int[N+1];
        Deque<Player> players = new LinkedList<>();

        String[] weapon = sc.readLine().split(" ");
        String[] health = sc.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            players.add(new Player(i + 1, Integer.parseInt(health[i]), Integer.parseInt(weapon[i])));
        }

        while (players.size() > 1) {
            Player turn = players.pollFirst();
            int hp = turn.health;
            int atk = turn.attack;
            // 이번 턴의 공격력은 이전 누적데미지 - (내 공격력 * 반복회수)
            int sum = totalAttack - (atk * repeat[turn.index]);

            // 이번 턴을 버텼다.
            if(hp > sum) {
                repeat[turn.index]+=1;
                totalAttack += atk; // 내 공격력을 추가하고 턴을 마친다.
                players.addLast(turn);
            }
        }

        System.out.println(players.poll().index);
    }

    public static class Player {
        int index;
        int health;
        int attack;

        public Player(int index, int health, int attack) {
            this.index = index;
            this.health = health;
            this.attack = attack;
        }
    }
}