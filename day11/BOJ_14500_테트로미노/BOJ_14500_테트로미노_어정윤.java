import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] paper;
    private static boolean[][] isVisited;
    private static int n;
    private static int m;
    private static int maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        isVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                isVisited[i][j] = true;
                dfs(i, j, 0, 0);
                isVisited[i][j] = false;
                searchZBlock(i, j);
            }
        }

        System.out.println(maxSum);
    }

    private static void searchZBlock(int x, int y) {
        // ㅜ
        if (x + 1 < n && y + 2 < m) {
            maxSum = Math.max(maxSum, paper[x][y] + paper[x][y + 1] + paper[x][y + 2] + paper[x + 1][y + 1]);
        }
        // ㅓ
        if (x + 2 < n && y - 1 >= 0) {
            maxSum = Math.max(maxSum, paper[x][y] + paper[x + 1][y] + paper[x + 2][y] + paper[x + 1][y - 1]);
        }
        // ㅗ
        if (x - 1 >= 0 && y + 2 < m) {
            maxSum = Math.max(maxSum, paper[x][y] + paper[x][y + 1] + paper[x][y + 2] + paper[x - 1][y + 1]);
        }
        // ㅏ
        if (x + 2 < n && y + 1 < m) {
            maxSum = Math.max(maxSum, paper[x][y] + paper[x + 1][y] + paper[x + 2][y] + paper[x + 1][y + 1]);
        }
    }

    private static void dfs(int x, int y, int cnt, int sum) {
        if (cnt == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int[] delta : DELTAS) {
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < n && dy >= 0 && dy < m && !isVisited[dx][dy]) {
                isVisited[dx][dy] = true;
                dfs(dx, dy, cnt + 1, sum + paper[dx][dy]);
                isVisited[dx][dy] = false;
            }
        }
    }
}
