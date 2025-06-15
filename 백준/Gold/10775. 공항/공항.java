import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static int[] gatewayList;

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int gateNum = Integer.parseInt(sc.readLine());
        int plainNum = Integer.parseInt(sc.readLine());

        // 주어진 P개의 입력값은
        // 사용할 수 있는 게이트 범위라고 생각하고 해결하자
        gatewayList = new int[gateNum + 1];

        for(int i = 1; i <= gateNum; i++) {
            gatewayList[i] = i;
        }

        // 어떻게 도킹을 처리해야 비행기를 최대한 많이 처리할 수 있을까?
        // 가장 빠른 방법은 주어진 숫자에 맞는 게이트에 도킹을 시도하고, 없다면
        // 사용가능한 가능한 큰 수 번호의 게이트에 도킹을 시도해보자

        // 0번째 게이트 = 더 이상 가용할 수 있는 게이트 X
        int count = 0;

        for(int i = 0; i < plainNum; i++) {
            int airPlainNumber = Integer.parseInt(sc.readLine());
            int availableGate = findAvailableGate(airPlainNumber);

            if(availableGate == 0) {
                break;
            }

            // 사용한 게이트는 더 이상 사용하지 않도록 처리
            compressFindRange(availableGate, findAvailableGate(availableGate - 1));
            count+=1;
        }

        System.out.println(count);
    }

    // 사용가능한 게이트 번호를 찾아보자
    public static int findAvailableGate(int gateNum) {
        if(gatewayList[gateNum] != gateNum) {
            gatewayList[gateNum] = findAvailableGate(gatewayList[gateNum]);
        }

        return gatewayList[gateNum];
    }

    // gateNumber는 현재 들어온 비행기 번호
    // compNumber는 해당 비행기 번호에서 마지막으로 탐색할 번호에 대해서 중복으로 사용하지 않고
    // 탐색 범위를 줄이도록 함
    public static void compressFindRange(int airPlainNumber, int compNumber) {
        gatewayList[airPlainNumber] = compNumber;
    }
}
