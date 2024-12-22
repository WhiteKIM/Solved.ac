import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static int[][] map;
    public static int[][] data;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int N;
    public static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        data = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        int x = 0;
        int y = 0;

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < M; j++) {
                int num = Integer.parseInt(input[j]);
                map[i][j] = num;

                if(num == 9) {
                    x = i;
                    y = j;
                    queue.add(new int[]{x, y, 0});
                    queue.add(new int[]{x, y, 1});
                    queue.add(new int[]{x, y, 2});
                    queue.add(new int[]{x, y, 3});
                }
            }
        }

        solved(queue);
        System.out.println(counting());
    }

    public static void solved(Queue<int[]> queue) {
        boolean[][][] visited = new boolean[4][N][M];

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            int index = arr[2];

            if(visited[index][x1][y1]) {
                continue;
            }

            visited[index][x1][y1] = true;
            data[x1][y1] = 1;

            int nx = x1 + dx[index];
            int ny = y1 + dy[index];

            if(0 <= nx && nx < N && 0 <= ny && ny < M) {
                if(map[nx][ny] == 1) {
                    if(index == 2) {
                        index = 3;
                    } else if(index == 3) {
                        index = 2;
                    }
                } else if(map[nx][ny] == 2) {
                    if(index == 0) {
                        index = 1;
                    } else if(index == 1) {
                        index = 0;
                    }

                } else if(map[nx][ny] == 3) {
                    if(index == 0) {
                        index = 3;
                    } else if(index == 1) {
                        index = 2;
                    } else if(index == 2) {
                        index = 1;
                    } else {
                        index = 0;
                    }

                } else if(map[nx][ny] == 4) {
                    if(index == 0) {
                        index = 2;
                    } else if(index == 1) {
                        index = 3;
                    } else if(index == 2) {
                        index = 0;
                    } else {
                        index = 1;
                    }
                }

                queue.add(new int[]{nx, ny, index});
            }
        }
    }

    public static int counting() {
        int count = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(data[i][j] == 1) {
                    count+=1;
                }
            }
        }

        return  count;
    }
}
