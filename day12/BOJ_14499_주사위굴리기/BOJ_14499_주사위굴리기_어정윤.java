import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기_어정윤 {

    private static final int[] DICE = new int[]{0, 0, 0, 0, 0, 0};
    private static final int[][] DIRECTIONS = new int[][]{{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private static final int EAST = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;

    // 4 2 1 5 6 3
    private static int firstSide = 2;
    private static int secondSide = 1;
    private static int thirdSide = 5;
    private static int fourthSide = 0;
    private static int fifthSide = 3;
    private static int sixthSide = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int x = Integer.parseInt(stringTokenizer.nextToken());
        int y = Integer.parseInt(stringTokenizer.nextToken());
        int commandNum = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < commandNum; i++) {
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int dx = x + DIRECTIONS[direction][0];
            int dy = y + DIRECTIONS[direction][1];
            if (dx >= 0 && dx < n && dy >= 0 && dy < m) {
                roll(direction);
                if (map[dx][dy] != 0) {
                    DICE[firstSide] = map[dx][dy];
                    map[dx][dy] = 0;
                } else {
                    map[dx][dy] = DICE[firstSide];
                }
                x = dx;
                y = dy;
                System.out.println(DICE[sixthSide]);
            }
        }
    }

    private static void roll(int direction) {
        int tmp = firstSide;
        if (direction == EAST) {
            firstSide = thirdSide;
            thirdSide = sixthSide;
            sixthSide = fourthSide;
            fourthSide = tmp;
        } else if (direction == WEST) {
            firstSide = fourthSide;
            fourthSide = sixthSide;
            sixthSide = thirdSide;
            thirdSide = tmp;
        } else if (direction == NORTH) {
            firstSide = secondSide;
            secondSide = sixthSide;
            sixthSide = fifthSide;
            fifthSide = tmp;
        } else {
            firstSide = fifthSide;
            fifthSide = sixthSide;
            sixthSide = secondSide;
            secondSide = tmp;
        }
    }
}
