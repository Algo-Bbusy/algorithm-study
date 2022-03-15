import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노_어정윤 {

    private static final int[][] TETROMINO_1 = {{0, 1}, {0, 1}, {1, -1}};   // ㅜ
    private static final int[][] TETROMINO_2 = {{1, 0}, {1, 0}, {-1, -1}};  // ㅓ
    private static final int[][] TETROMINO_3 = {{0, 1}, {0, 1}, {-1, -1}};    // ㅗ
    private static final int[][] TETROMINO_4 = {{1, 0}, {1, 0}, {-1, 1}};    // ㅏ
    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static boolean[][] isVisited;
    private static int n;
    private static int m;
    private static int minSum;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                isVisited = new boolean[n][m];
                dfs(paper, i, j, 0, 0);
            }
        }

        // ㅜ모양은 따로
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 2; j++) {
                int sum = paper[i][j];
                int dx = i;
                int dy = j;
                for (int[] tetromino : TETROMINO_1) {
                    dx += tetromino[0];
                    dy += tetromino[1];
                    sum += paper[dx][dy];
                }
                minSum = Math.max(minSum, sum);
            }
        }

        // ㅓ
        for (int i = 0; i < n - 2; i++) {
            for (int j = 1; j < m; j++) {
                int sum = paper[i][j];
                int dx = i;
                int dy = j;
                for (int[] tetromino : TETROMINO_2) {
                    dx += tetromino[0];
                    dy += tetromino[1];
                    sum += paper[dx][dy];
                }
                minSum = Math.max(minSum, sum);
            }
        }

        // ㅗ
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m - 2; j++) {
                int sum = paper[i][j];
                int dx = i;
                int dy = j;
                for (int[] tetromino : TETROMINO_3) {
                    dx += tetromino[0];
                    dy += tetromino[1];
                    sum += paper[dx][dy];
                }
                minSum = Math.max(minSum, sum);
            }
        }

        // ㅏ
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 1; j++) {
                int sum = paper[i][j];
                int dx = i;
                int dy = j;
                for (int[] tetromino : TETROMINO_4) {
                    dx += tetromino[0];
                    dy += tetromino[1];
                    sum += paper[dx][dy];
                }
                minSum = Math.max(minSum, sum);
            }
        }

        System.out.println(minSum);
    }

    private static void dfs(int[][] paper, int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            minSum = Math.max(minSum, sum);
            return;
        }

        for (int[] delta : DELTAS) {
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < n && dy >= 0 && dy < m && !isVisited[dx][dy]) {
                isVisited[dx][dy] = true;
                dfs(paper, dx, dy, cnt + 1, sum + paper[dx][dy]);
                isVisited[dx][dy] = false;
            }
        }
    }
}
