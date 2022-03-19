import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int[][] heightInfo;
    private static boolean[][] isVisited;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        heightInfo = new int[n][n];
        int maxHeight = 0;
        int minHeight = 100;
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                heightInfo[i][j] = input;
                if (input > maxHeight) {
                    maxHeight = input;
                } else if (input < minHeight) {
                    minHeight = input;
                }
            }
        }

        int maxSafeArea = 1;
        for (int i = minHeight; i < maxHeight; i++) {
            maxSafeArea = Math.max(maxSafeArea, getSafeArea(i));
        }
        System.out.println(maxSafeArea);
    }

    private static int getSafeArea(int sunkHeight) {
        isVisited = new boolean[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j] && heightInfo[i][j] > sunkHeight) {
                    cnt++;
                    isVisited[i][j] = true;
                    searchArea(i, j, sunkHeight);
                }
            }
        }
        return cnt;
    }

    private static void searchArea(int x, int y, int sunkHeight) {
        for (int[] delta : DELTAS) {
            int dx = x + delta[0];
            int dy = y + delta[1];
            if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy] && heightInfo[dx][dy] > sunkHeight) {
                isVisited[dx][dy] = true;
                searchArea(dx, dy, sunkHeight);
            }
        }
    }
}
