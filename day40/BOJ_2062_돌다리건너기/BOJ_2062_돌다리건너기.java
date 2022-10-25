package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2062_돌다리건너기_어정윤 {

    private static final String DELIMITER = "";

    private static String[] input;
    private static String[] bridgeOfAngel;
    private static String[] bridgeOfDevil;
    private static int passCount;
    private static int length;
    private static int way;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        input = bufferedReader.readLine().split(DELIMITER);
        bridgeOfAngel = bufferedReader.readLine().split(DELIMITER);
        bridgeOfDevil = bufferedReader.readLine().split(DELIMITER);
        passCount = input.length;
        length = bridgeOfAngel.length;
        crossBridge(0, 0, false);
        crossBridge(0, 0, true);
        System.out.println(way);
    }

    private static void crossBridge(int cnt, int current, boolean wasAngel) {
        if (cnt == passCount) {
            way++;
            return;
        }

        if (current == length) {
            return;
        }

        if (!wasAngel && bridgeOfAngel[current].equals(input[cnt])) {
            crossBridge(cnt + 1, current + 1, true);
        }
        if (wasAngel && bridgeOfDevil[current].equals(input[cnt])) {
            crossBridge(cnt + 1, current + 1, false);
        }
        crossBridge(cnt, current + 1, wasAngel);
    }
}
