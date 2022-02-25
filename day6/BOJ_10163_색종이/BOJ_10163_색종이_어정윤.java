import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10163_색종이_어정윤 {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final int SIZE = 1001;
    private static final int[][] SPACE = new int[SIZE][SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int startY = Integer.parseInt(stringTokenizer.nextToken());
            int startX = Integer.parseInt(stringTokenizer.nextToken());
            int width = Integer.parseInt(stringTokenizer.nextToken());
            int height = Integer.parseInt(stringTokenizer.nextToken());
            for (int x = startX; x < startX + height; x++) {
                for (int y = startY; y < startY + width; y++) {
                    SPACE[x][y] = i;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int x = 0; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    if (SPACE[x][y] == i) {
                        sum++;
                    }
                }
            }
            STRING_BUILDER.append(sum)
                    .append("\n");
        }
        System.out.println(STRING_BUILDER);
    }
}
