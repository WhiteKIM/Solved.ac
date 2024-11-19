import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static char[] target = null;
    public static int N = 0;
    public static int result = Integer.MAX_VALUE;
    public static List<Book> bookShelf = new ArrayList<>();
    public static int[] arr1 = new int[26];
    public static int[] arr2 = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        target = sc.readLine().toCharArray();
        N = Integer.parseInt(sc.readLine());

        for(int i = 0; i < N; i++) {
            String[] input = sc.readLine().split(" ");
            bookShelf.add(new Book(Integer.parseInt(input[0]), input[1]));
        }

        for(char c : target) {
            int index = (int)c - 'A';
            arr1[index]+=1;
        }

        solved(0, 0);

        if(result == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(result);
    }


    public static void solved(int idx, int price) {
        if(idx == N) {
            if(isValid()) {
                result = Math.min(result, price);
            }
            return;
        }

        Book book = bookShelf.get(idx);
        for(int i = 0; i < book.title.length(); i++) {
            int index  = (int)book.title.charAt(i) - 'A';
            arr2[index]+=1;
        }

        solved(idx + 1, price + bookShelf.get(idx).price);

        for(int i = 0; i < book.title.length(); i++) {
            int index  = (int)book.title.charAt(i) - 'A';
            arr2[index]-=1;
        }

        solved(idx + 1, price);
    }

    public static boolean isValid() {
        for(int i = 0; i < 26; i++) {
            if(arr1[i] > arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static class Book {
        int price;
        String title;

        public Book(int price, String title) {
            this.price = price;
            this.title = title;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + price;
            result = prime * result + ((title == null) ? 0 : title.hashCode());
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
            Book other = (Book) obj;
            if (price != other.price)
                return false;
            if (title == null) {
                if (other.title != null)
                    return false;
            } else if (!title.equals(other.title))
                return false;
            return true;
        }
    }
}
