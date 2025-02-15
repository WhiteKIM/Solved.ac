import java.util.*;

public class Main {
    public static int[][] map;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int N;
    public static int M;
    public static int fuel = 0;
    public static ArrayList<Customer> customerList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        fuel = Integer.parseInt(input[2]);

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            input = sc.nextLine().split(" ");

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        input = sc.nextLine().split(" ");
        int taxi_x = Integer.parseInt(input[0]) - 1;
        int taxi_y = Integer.parseInt(input[1]) - 1;

        for(int i = 0; i < M; i++) {
            input = sc.nextLine().split(" ");
            int sx = Integer.parseInt(input[0]) - 1;
            int sy = Integer.parseInt(input[1]) - 1;
            int ex = Integer.parseInt(input[2]) - 1;
            int ey = Integer.parseInt(input[3]) - 1;

            customerList.add(new Customer(i+1, sx, sy, ex, ey));
        }

        for(int i = 0; i < M; i++) {
            Queue<Customer> customers = new PriorityQueue<>();
            if(fuel > 0) {
                // 태울 수 있는 승객을 찾는다.
                customers = find_customer(taxi_x, taxi_y);
            }

            Customer customer = null;

            if(!customers.isEmpty()) {
                customer = customers.poll();
            } else {
                break;
            }

            if(customer != null) {
                fuel -= customer.move;  // 먼저 이동하면서 소모한 연로를 반영
                
                // 연료가 부족하당
                if(fuel < 0) {
                    System.out.println(-1);
                    return;
                }
                
                // 해당 승객을 목적지까지 이동시킨다.
                int[] taxi_loc = goDestination(customer);

                // 목적지까지 이동완료
                if(taxi_loc != null) {
                    taxi_x = taxi_loc[0];
                    taxi_y = taxi_loc[1];
                    int taxi_fuel = taxi_loc[2];

                    if(fuel < taxi_fuel) {
                        System.out.println(-1);
                        return;
                    }

                    fuel += taxi_fuel;
                    customerList.remove(customer);
                } else {
                    System.out.println(-1);
                    return;
                }
            }
        }

        if(customerList.isEmpty()) {
            System.out.println(fuel);
        } else {
            System.out.println(-1);
        }
    }

    // 태울 수 있는 승객을 태운다.
    private static Queue<Customer> find_customer(int taxiX, int taxiY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[]{taxiX, taxiY, 0});
        visited[taxiX][taxiY] = true;
        Queue<Customer> result = new PriorityQueue<>();

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int tx = arr[0];
            int ty = arr[1];
            int move = arr[2];

            if (fuel - move < 0) {
                continue;
            }

            for(Customer c : customerList) {
                if(c.start_x == tx && c.start_y == ty) {
                    c.setMove(move);
                    result.add(c);
                }
            }

            for(int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, move+1});
                    }
                }
            }
        }

        return result;
    }

    private static int[] goDestination(Customer customer) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[]{customer.start_x, customer.start_y, 0});
        visited[customer.start_x][customer.start_y] = true;

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int tx = arr[0];
            int ty = arr[1];
            int move = arr[2];

            if(customer.end_x == tx && customer.end_y == ty) {
                return new int[]{customer.end_x, customer.end_y, move};
            }

            for(int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < N) {
                    if(!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny, move+1});
                    }
                }
            }
        }

        return null;
    }

    public static class Customer implements Comparable<Customer>{
        int num;
        int start_x;
        int start_y;
        int end_x;
        int end_y;
        int move;

        public Customer(int num, int start_x, int start_y, int end_x, int end_y) {
            this.num = num;
            this.start_x = start_x;
            this.start_y = start_y;
            this.end_x = end_x;
            this.end_y = end_y;
            this.move = 0;
        }

        public void setMove(int move) {
            this.move = move;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Customer customer = (Customer) o;
            return start_x == customer.start_x && start_y == customer.start_y && end_x == customer.end_x && end_y == customer.end_y;
        }

        @Override
        public int hashCode() {
            int result = start_x;
            result = 31 * result + start_y;
            result = 31 * result + end_x;
            result = 31 * result + end_y;
            return result;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "num=" + num +
                    ", start_x=" + start_x +
                    ", start_y=" + start_y +
                    ", end_x=" + end_x +
                    ", end_y=" + end_y +
                    ", move=" + move +
                    '}';
        }

        @Override
        public int compareTo(Customer o) {
            if (this.move != o.move) {
                return this.move - o.move; // 가까운 승객 먼저
            }

            if(this.start_x != o.start_x) {
                return this.start_x - o.start_x;
            }

            return this.start_y - o.start_y;
        }
    }
}
