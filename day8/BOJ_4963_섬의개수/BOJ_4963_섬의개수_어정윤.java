import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4963_섬의개수_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private static final int LAND = 1;
    private static final int VISIT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;
        while (true) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            int h = Integer.parseInt(stringTokenizer.nextToken());
            if (w + h == 0) {
                break;
            }
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
            }
            stringBuilder.append(getIsland(map, h, w))
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }

    private static int getIsland(int[][] map, int h, int w) {
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == LAND) {
                    cnt++;
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] location = queue.poll();
                        int x = location[0];
                        int y = location[1];
                        map[x][y] = VISIT;
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < h && dy >= 0 && dy < w && map[dx][dy] == LAND) {
                                map[dx][dy] = VISIT;
                                queue.offer(new int[]{dx, dy});
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
