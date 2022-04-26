import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_마법사상어와파이어스톰_어정윤 {

    private static final int[][] DELTAS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int[][] map;
    private static int[][] copiedMap;
    private static int totalIce;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int q = Integer.parseInt(stringTokenizer.nextToken());
        int size = 1 << n;
        map = new int[size][size];
        for (int i = 0; i < size; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < size; j++) {
                int ice = Integer.parseInt(stringTokenizer.nextToken());
                map[i][j] = ice;
                totalIce += ice;
            }
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int l = 0; l < q; l++) {
            int level = Integer.parseInt(stringTokenizer.nextToken());
            if (level == 0) {
                melt(size);
                continue;
            }

            int partSize = 1 << level;
            copiedMap = copyMap(size);
            for (int i = 0; i < size; i += partSize) {
                for (int j = 0; j < size; j += partSize) {
                    rotate(i, j, partSize);
                }
            }
            map = copiedMap;
            melt(size);
        }

        int maxIceSize = 0;
        if (totalIce != 0) {
            maxIceSize = getMaxIceSize(size, maxIceSize);
        }
        System.out.println(totalIce);
        System.out.println(maxIceSize);
    }

    private static int[][] copyMap(int size) {
        int[][] copiedMap = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
        return copiedMap;
    }

    private static void melt(int size) {
        int[][] copiedMap = copyMap(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int ices = 0;
                for (int[] delta : DELTAS) {
                    int dx = i + delta[0];
                    int dy = j + delta[1];
                    if (dx >= 0 && dx < size && dy >= 0 && dy < size && copiedMap[dx][dy] != 0) {
                        ices++;
                    }
                }
                if (ices < 3 && map[i][j] > 0) {
                    map[i][j]--;
                    totalIce--;
                }
            }
        }
    }

    private static int getMaxIceSize(int size, int maxIceSize) {
        boolean[][] isVisited = new boolean[size][size];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int iceSize = 1;
                if (map[i][j] != 0 && !isVisited[i][j]) {
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        isVisited[x][y] = true;
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < size && dy >= 0 && dy < size && map[dx][dy] != 0 && !isVisited[dx][dy]) {
                                isVisited[dx][dy] = true;
                                iceSize++;
                                queue.offer(new int[]{dx, dy});
                            }
                        }
                    }
                }
                if (iceSize > maxIceSize) {
                    maxIceSize = iceSize;
                }
            }
        }
        return maxIceSize;
    }

    private static void rotate(int x, int y, int partSize) {
        int boundary = partSize * 4 - 4;
        while (boundary > 0) {
            for (int i = 0; i < partSize-1; i++) {
                int endX = x + partSize - 1;
                int endY = y + partSize - 1;
                copiedMap[x+i][endY] = map[x][y+i];  // 위쪽 -> 오른쪽
                copiedMap[endX][endY - i] = map[x+i][endY]; // 오른쪽 -> 아래쪽
                copiedMap[endX - i][y] = map[endX][endY - i]; // 아래쪽 -> 왼쪽
                copiedMap[x][y+i] = map[endX-i][y];   // 왼쪽 -> 위쪽
            }
            x++;
            y++;
            boundary -= 8;
            partSize -= 2;
        }
    }
}
