import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2210_숫자판점프_어정윤 {

    private static final int[][] DELTAS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static final List<Integer> RESULTS = new ArrayList<>();
    private static final int SIZE = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                dfs(board, i, j, board[i][j], 1);
            }
        }

        int result = (int) RESULTS.stream()
                .distinct()
                .count();
        System.out.println(result);
    }

    private static void dfs(int[][] board, int x, int y, int number, int cnt) {
        if (cnt == 6) {
            RESULTS.add(number);
            return;
        }

        for (int[] delta : DELTAS) {
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < SIZE && dy >= 0 && dy < SIZE) {
                dfs(board, dx, dy, number*10+board[dx][dy], cnt+1);
            }
        }
    }
}
