import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2578_빙고_어정윤 {

    private static final int[][] DELTAS = {
            {-1, 0}, {1, 0},    // |
            {0, -1}, {0, 1},    // -
            {1, -1}, {-1, 1},   // /
            {-1, -1}, {1, 1}    // \
    };
    private static final int OPPOSITE_SIDE = 1;
    private static final int BOARD_SIDE = 5;
    private static final int X = 0;
    private static final int Y = 1;

    private static int[][] bingoBoard;
    private static int bingo;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bingoBoard = new int[BOARD_SIDE][BOARD_SIDE];
        for (int i = 0; i < BOARD_SIDE; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < BOARD_SIDE; j++) {
                bingoBoard[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < BOARD_SIDE; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < BOARD_SIDE; j++) {
                cnt++;
                delete(Integer.parseInt(stringTokenizer.nextToken()));
                if (bingo >= 3) {
                    System.out.println(cnt);
                    System.exit(0);
                }
            }
        }
    }

    private static void delete(int number) {
        for (int i = 0; i < BOARD_SIDE; i++) {
            for (int j = 0; j < BOARD_SIDE; j++) {
                if (bingoBoard[i][j] == number) {
                    bingoBoard[i][j] = 0;
                    checkBingo(i, j);
                    return;
                }
            }
        }
    }

    private static void checkBingo(int x, int y) {
        for (int i = 0; i < DELTAS.length; i += 2) {
            int check = 1;
            for (int direction = i; direction <= i + OPPOSITE_SIDE; direction++) {
                int dx = x + DELTAS[direction][X];
                int dy = y + DELTAS[direction][Y];
                while (dx >= 0 && dx < BOARD_SIDE && dy >= 0 && dy < BOARD_SIDE && bingoBoard[dx][dy] == 0) {
                    dx += DELTAS[direction][X];
                    dy += DELTAS[direction][Y];
                    check++;
                }
                if (dx < 0 || dy < 0) {
                    continue;
                }
                break;
            }
            if (check == BOARD_SIDE) {
                bingo++;
            }
        }
    }
}
