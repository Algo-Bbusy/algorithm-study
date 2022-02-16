import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2116_주사위쌓기_어정윤 {

    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int diceNum = Integer.parseInt(bufferedReader.readLine());
        int[][] dices = new int[diceNum][6];
        for (int i = 0; i < diceNum; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            int f = Integer.parseInt(stringTokenizer.nextToken());
            dices[i] = new int[]{a, f, b, d, c, e};
        }

        for (int i = 0;i < 6;i++) {
            int sideMaxSum = 0;
            int overIdx = i;
            for (int j = 0; j < diceNum; j++) {
                int underIdx;
                if (overIdx % 2 == 0) {
                    underIdx = overIdx+1;
                } else {
                    underIdx = overIdx-1;
                }
                int under = dices[j][underIdx];
                int sideMax = 0;
                for (int k = 0; k < 6; k++) {
                    if (k != underIdx && k != overIdx) {
                        sideMax = Math.max(sideMax, dices[j][k]);
                    }
                }
                sideMaxSum += sideMax;

                if (j < diceNum-1) {
                    for (int k = 0; k < 6; k++) {
                        if (dices[j+1][k] == under) {
                            overIdx = k;
                            break;
                        }
                    }
                }
            }
            max = Math.max(max, sideMaxSum);
        }
        System.out.println(max);
    }
}
