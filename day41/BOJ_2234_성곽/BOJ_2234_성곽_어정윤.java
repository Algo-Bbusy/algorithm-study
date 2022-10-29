import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_성곽_어정윤 {

    private static final int[][] DELTAS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static final int EMPTY = 0;

    private static int[][] castle;
    private static int n;
    private static int m;
    private static int room;
    private static int maxArea;
    private static int maxAreaWithBrokenWall;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        castle = new int[m][n];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                castle[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        searchCastle();
        System.out.printf("%d\n%d\n%d", room, maxArea, maxAreaWithBrokenWall);
    }

    private static void searchCastle() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[m][n];
        Queue<int[]> queueWithBreakingWall = new ArrayDeque<>();
        boolean[][] isVisitedWithBreakingWall = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j]) {
                    room++;
                    queue.offer(new int[]{i, j});
                    isVisited[i][j] = true;
                    int area = 1;
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int direction = 0, deltasLength = DELTAS.length; direction < deltasLength; direction++) {
                            if ((castle[x][y] & (1 << direction)) == EMPTY) {
                                int dx = x + DELTAS[direction][0];
                                int dy = y + DELTAS[direction][1];
                                if (dx >= 0 && dx < m && dy >= 0 && dy < n && !isVisited[dx][dy]) {
                                    queue.offer(new int[]{dx, dy});
                                    isVisited[dx][dy] = true;
                                    area++;
                                }
                            } else {
                                queueWithBreakingWall.offer(new int[]{x, y});
                                isVisitedWithBreakingWall[x][y] = true;
                                castle[x][y] &= ~(1 << direction);
                                breakWall(area, queueWithBreakingWall, isVisited, isVisitedWithBreakingWall);
                                castle[x][y] |= (1 << direction);
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
    }

    private static void breakWall(int area, Queue<int[]> queueWithBreakingWall, boolean[][] isVisited, boolean[][] isVisitedWithBreakingWall) {
        while (!queueWithBreakingWall.isEmpty()) {
            int[] current = queueWithBreakingWall.poll();
            int x = current[0];
            int y = current[1];
            for (int direction = 0, deltasLength = DELTAS.length; direction < deltasLength; direction++) {
                if ((castle[x][y] & (1 << direction)) == EMPTY) {
                    int dx = x + DELTAS[direction][0];
                    int dy = y + DELTAS[direction][1];
                    if (dx >= 0 && dx < m && dy >= 0 && dy < n && !isVisited[dx][dy] && !isVisitedWithBreakingWall[dx][dy]) {
                        queueWithBreakingWall.offer(new int[]{dx, dy});
                        isVisitedWithBreakingWall[dx][dy] = true;
                        area++;
                    }
                }
            }
        }
        maxAreaWithBrokenWall = Math.max(maxAreaWithBrokenWall, area);
        initailize(isVisitedWithBreakingWall);
    }

    private static void initailize(boolean[][] isVisitedWithBreakingWall) {
        for (int i = 0; i < m; i++) {
            Arrays.fill(isVisitedWithBreakingWall[i], false);
        }
    }
}
