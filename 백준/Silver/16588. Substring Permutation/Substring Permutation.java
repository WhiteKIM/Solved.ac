import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] str1 = sc.nextLine().toCharArray();
        char[] str2 = sc.nextLine().toCharArray();

        int[] arr1 = new int[26];
        int[] arr2 = new int[26];

        for(char c : str1) {
            int index = (int)c - 'a';
            arr1[index]++;
        }

        for(char c : str2) {
            int index = (int)c - 'a';
            arr2[index]++;
        }
        
        boolean check = true;
        for(int i = 0; i < 26; i++) {
            if(arr1[i] < arr2[i]) {
                check = false;
            }
        }

        if(check)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
