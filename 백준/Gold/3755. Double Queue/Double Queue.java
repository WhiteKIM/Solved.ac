import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        DoubleQueue<Peer> queue = new DoubleQueue<>();

        while (true) {
            String[] input = br.readLine().split(" ");
            int command = Integer.parseInt(input[0]);

            if(command == 0)
                break;
            
            int number = -1;
            int priority = -1;

            switch (command) {
                case 1:
                    number = Integer.parseInt(input[1]);
                    priority = Integer.parseInt(input[2]);
                    queue.addItem(new Peer(number, priority));
                    break;
                case 2:
                    Peer peer = queue.getHighRank();
                    if(peer != null) {
                        bw.write(peer.number+"\n");
                        queue.removeItem(peer);
                    } else {
                        bw.write(0+"\n");
                    }
                    break;
                case 3:
                Peer peer2 = queue.getLowRank();
                if(peer2 != null) {
                    bw.write(peer2.number+"\n");
                    queue.removeItem(peer2);
                } else {
                    bw.write(0+"\n");
                }
                    break;
                default:
                    break;
            }
        }

        br.close();
        bw.close();
    }

    public static class DoubleQueue<T> {
        PriorityQueue<T> highRankQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<T> lowRankQueue = new PriorityQueue<>();
        int size = 0;

        public void addItem(T item) {
            highRankQueue.add(item);
            lowRankQueue.add(item);
            size+=1;
        }

        public void removeItem(T item) {
            highRankQueue.remove(item);
            lowRankQueue.remove(item);
            size-=1;
        }

        public T getHighRank() {
            if(size > 0)
                return highRankQueue.peek();
            else
                return null;
        }

        public T getLowRank() {
            if(size > 0)
                return lowRankQueue.peek();
            else
                return null;
        }
    }

    public static class Peer implements Comparable{
        int number;
        int priority;

        public Peer(int number, int priority) {
            this.number = number;
            this.priority = priority;
        }

        @Override
        public int compareTo(Object o) {
            Peer comp = (Peer) o;
            return Integer.compare(priority, comp.priority);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + number;
            result = prime * result + priority;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Peer other = (Peer) obj;
            if (number != other.number)
                return false;
            if (priority != other.priority)
                return false;
            return true;
        }
    }
}