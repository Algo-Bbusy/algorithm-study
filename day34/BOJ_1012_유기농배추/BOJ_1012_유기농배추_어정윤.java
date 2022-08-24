import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012_유기농배추_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final Queue<int[]> CHINESE_CABBAGES = new LinkedList<>();
    private static final int CHINESE_CABBAGE = 1;
    private static final int EAT = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < testCase; t++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int width = Integer.parseInt(stringTokenizer.nextToken());
            int height = Integer.parseInt(stringTokenizer.nextToken());
            int[][] farm = new int[height][width];
            int chineseCabbageCount = Integer.parseInt(stringTokenizer.nextToken());
            for (int i = 0; i < chineseCabbageCount; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int y = Integer.parseInt(stringTokenizer.nextToken());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                farm[x][y] = CHINESE_CABBAGE;
            }

            answer.append(getInsectCount(width, height, farm))
                    .append("\n");
        }
        System.out.println(answer);
    }

    private static int getInsectCount(int width, int height, int[][] farm) {
        int insectCount = 0;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (farm[x][y] == CHINESE_CABBAGE) {
                    insectCount++;
                    CHINESE_CABBAGES.offer(new int[]{x, y});
                    while (!CHINESE_CABBAGES.isEmpty()) {
                        int[] current = CHINESE_CABBAGES.poll();
                        int currentX = current[0];
                        int currentY = current[1];
                        farm[currentX][currentY] = EAT;
                        for (int[] delta : DELTAS) {
                            int dx = currentX + delta[0];
                            int dy = currentY + delta[1];
                            if (dx >= 0 && dx < height && dy >= 0 && dy < width && farm[dx][dy] == CHINESE_CABBAGE) {
                                farm[dx][dy] = EAT;
                                CHINESE_CABBAGES.offer(new int[]{dx, dy});
                            }
                        }
                    }
                }
            }
        }
        return insectCount;
    }
}
