import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기_어정윤 {

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int CLEAN = 2;

    private static int n;
    private static int m;
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int x = Integer.parseInt(stringTokenizer.nextToken());
        int y = Integer.parseInt(stringTokenizer.nextToken());
        int direction = Integer.parseInt(stringTokenizer.nextToken());
        int[][] space = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                space[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        operate(space, x, y, direction);
    }

    private static void operate(int[][] space, int x, int y, int direction) {
        if (space[x][y] != CLEAN) {
            space[x][y] = CLEAN;
            cnt++;
        }
        searchFourDirections(space, x, y, direction);
    }

    private static void searchFourDirections(int[][] space, int x, int y, int direction) {
        for (int i = 0; i < 4; i++) {
            int left = (direction - 1 + DELTAS.length) % DELTAS.length;
            int dx = x + DELTAS[left][0];
            int dy = y + DELTAS[left][1];
            if (dx >= 0 && dx < n && dy >= 0 && dy < m && space[dx][dy] == EMPTY) {
                operate(space, dx, dy, left);
            } else {
                direction = left;
            }
        }
        int back = (direction + 2) % DELTAS.length;
        int dx = x + DELTAS[back][0];
        int dy = y + DELTAS[back][1];
        if (dx >= 0 && dx < n && dy >= 0 && dy < m && space[dx][dy] != WALL) {
            searchFourDirections(space, dx, dy, direction);
        } else {
            System.out.println(cnt);
            System.exit(0);
        }
    }
}
