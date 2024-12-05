import java.util.Scanner;

public class Main {
    public static char[] left = "qwertasdfgzxcvb".toCharArray();
    public static char[] right = "yuiophjklnm".toCharArray();
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();
        boolean check = true;

        boolean lr = true;
        // 왼쪽 - true
        // 오른쪽 - false;
        lr = isLeft(input[0]);

        for(int i = 1; i < input.length; i++) {
            if(lr) {    //이전 왼쪽
                if(!isRight(input[i])) {
                    lr = false;
                } else {
                    check = false;
                    break;
                }
            } else {    // 이전 오른쪽
                if(isLeft(input[i])) {
                    lr = true;
                } else {
                    check = false;
                    break;
                }
            }
        }


        if(check) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public static boolean isLeft(char ch) {
        for(char l : left) {
            if(ch == l) {
                return true;
            }
        }

        return false;
    }

    public static boolean isRight(char ch) {
        for(char r : right) {
            if(ch == r) {
                return false;
            }
        }

        return true;
    }
}
