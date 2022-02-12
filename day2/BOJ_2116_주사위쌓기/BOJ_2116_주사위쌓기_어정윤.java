import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2116_주사위쌓기_어정윤 {

    private static int[][] dices;
    private static int[][] sides;
    private static int under;
    private static int max;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        n = Integer.parseInt(bufferedReader.readLine());
        dices = new int[n][6];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int e = Integer.parseInt(stringTokenizer.nextToken());
            int f = Integer.parseInt(stringTokenizer.nextToken());
            dices[i] = new int[]{a, f, b, d, c, e};
        }

        sides = new int[n][4];
        int underIdx = 0;
        for (int i = 0;i < 6;i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                if (underIdx % 2 == 0) {
                    under = dices[j][++underIdx];
                } else {
                    under = dices[j][--underIdx];
                }

                for (int k = 0; k < 6; k++) {
                    int overIdx;
                    if (underIdx % 2 == 0) {
                        overIdx = underIdx + 1;
                    } else {
                        overIdx = underIdx - 1;
                    }
                    if (k != underIdx && k != overIdx) {
                        sides[j][cnt++] = dices[j][k];
                    }
                }

                if (j < n-1) {
                    for (int k = 0; k < 6; k++) {
                        if (dices[j+1][k] == under) {
                            underIdx = k;
                            break;
                        }
                    }
                }
            }
            getMax();
        }
        System.out.println(max);
    }

    private static void getMax() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Arrays.stream(sides[i])
                    .max()
                    .getAsInt();
        }
        max = Math.max(max, sum);
    }
}
