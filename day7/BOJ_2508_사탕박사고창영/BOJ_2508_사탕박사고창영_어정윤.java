import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2508_사탕박사고창영_어정윤 {

    private static final char LEFT = '>';
    private static final char RIGHT = '<';
    private static final char UP = 'v';
    private static final char DOWN = '^';

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
                        int left = y - 1;
                        int right = y + 1;
                        int up = x - 1;
                        int down = x + 1;
                        if (left >= 0 && right < c && box[x][left] == LEFT && box[x][right] == RIGHT) {
                            candy++;
                        }
                        if (up >= 0 && down < r && box[up][y] == UP && box[down][y] == DOWN) {
                            candy++;
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
