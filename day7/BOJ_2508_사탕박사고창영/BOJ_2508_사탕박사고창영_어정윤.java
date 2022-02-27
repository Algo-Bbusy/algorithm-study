import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2508_사탕박사고창영_어정윤 {

    private static final int[][] DELTAS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static final char[] WRAPPER = {'>', '<', 'v', '^'};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        StringBuilder stringBuilder = new StringBuilder();
        int testCase = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < testCase; t++) {
            bufferedReader.readLine();
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            char[][] box = new char[r][c];
            for (int i = 0; i < r; i++) {
                String input = bufferedReader.readLine();
                for (int j = 0; j < c; j++) {
                    box[i][j] = input.charAt(j);
                }
            }

            int candy = 0;
            for (int x = 0; x < r; x++) {
                for (int y = 0; y < c; y++) {
                    if (box[x][y] == 'o') {
                        for (int k = 0; k < DELTAS.length; k += 2) {
                            boolean isCandy = true;
                            for (int l = k; l < k + 2; l++) {
                                int dx = x + DELTAS[l][0];
                                int dy = y + DELTAS[l][1];
                                if (dx < 0 || dx >= r || dy < 0 || dy >= c || box[dx][dy] != WRAPPER[l]) {
                                    isCandy = false;
                                    break;
                                }
                            }
                            if (isCandy) {
                                candy++;
                            }
                        }
                    }
                }
            }
            stringBuilder.append(candy)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }
}
