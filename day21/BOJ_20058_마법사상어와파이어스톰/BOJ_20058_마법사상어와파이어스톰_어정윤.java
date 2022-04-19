package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_마법사상어와파이어스톰_어정윤 {

    private static final int[][] DELTAS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int[][] MOVE_DELTAS = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    private static int[][] map;
    private static int[][] copiedMap;

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
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int l = 0; l < q; l++) {
            int partSize = 1 << Integer.parseInt(stringTokenizer.nextToken());
            copiedMap = copyMap(size);
            for (int i = 0; i < size; i += partSize) {
                for (int j = 0; j < size; j += partSize) {
                    rotate(i, j, partSize);
                }
            }
            melt(size);
        }

        int sum = getSum();
        int maxIceSize = 0;
        if (sum != 0) {
            maxIceSize = getMaxIceSize(size, maxIceSize);
        }
        System.out.println(sum);
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int ices = 0;
                for (int[] delta : DELTAS) {
                    int dx = i + delta[0];
                    int dy = j + delta[1];
                    if (dx >= 0 && dx < size && dy >= 0 && dy < size && map[dx][dy] != 0) {
                        ices++;
                    }
                }
                if (ices < 3) {
                    map[i][j]--;
                }
            }
        }
    }

    private static int getSum() {
        int sum = 0;
        for (int[] ices : map) {
            for (int ice : ices) {
                sum += ice;
            }
        }
        return sum;
    }

    private static int getMaxIceSize(int size, int maxIceSize) {
        boolean[][] isVisited = new boolean[size][size];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int iceSize = 1;
                if (!isVisited[i][j]) {
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < size && dy >= 0 && dy < size && map[dx][dy] != 0) {
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

    // 여기가 문제인데 시간 내에 수정하지 못함
    private static void rotate(int x, int y, int partSize) {
        int halfSize = partSize / 2;
        int direction = 0;
        for (int i = x; i < x + partSize; i += halfSize) {
            for (int j = y; j < y + partSize; j += halfSize) {
                if (direction == 4) {
                    direction = 0;
                }

                for (int k = i; k < i + halfSize; k++) {
                    for (int l = j; l < j + halfSize; l++) {
                        int dx = k + (MOVE_DELTAS[direction][0] * halfSize);
                        int dy = l + (MOVE_DELTAS[direction][1] * halfSize);
                        map[dx][dy] = copiedMap[k][l];
                    }
                }
                direction++;
            }
        }
    }
}
