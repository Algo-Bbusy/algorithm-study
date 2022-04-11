import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] deltas = new int[][]{{0, 0}, {0, n - 1}, {n - 1, n - 1}, {n - 1, 0}, {n - 1, 1},
                {0, 1}, {1, 1}, {1, 0}, {1, n - 1}};
        int[][] diagonals = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        // 초기 구름 생성
        boolean[][] clouds = new boolean[n][n];
        clouds[n - 1][0] = true;
        clouds[n - 1][1] = true;
        clouds[n - 2][0] = true;
        clouds[n - 2][1] = true;

        boolean[][] wasThereCloud;
        for (int i = 0; i < m; i++) {
            wasThereCloud = new boolean[n][n];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int moveNum = Integer.parseInt(stringTokenizer.nextToken());
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (clouds[x][y]) {
                        // 1. 모든 구름이 di 방향으로 si칸 이동
                        int nextX = (x + deltas[direction][0] * moveNum) % n;
                        int nextY = (y + deltas[direction][1] * moveNum) % n;
                        wasThereCloud[nextX][nextY] = true;
                        // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양 1 증가
                        map[nextX][nextY]++;
                    }
                }
            }

            // 4. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물의 양 증가
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (wasThereCloud[x][y]) {
                        int basketWithWater = 0;
                        for (int[] diagonal : diagonals) {
                            int dx = x + diagonal[0];
                            int dy = y + diagonal[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n && map[dx][dy] != 0) {
                                basketWithWater++;
                            }
                        }
                        map[x][y] += basketWithWater;
                    }
                }
            }

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    // 3. 구름이 사라짐
                    if (clouds[x][y]) {
                        clouds[x][y] = false;
                    }
                    // 5. 새로운 구름 생성
                    if (map[x][y] >= 2 && !wasThereCloud[x][y]) {
                        clouds[x][y] = true;
                        map[x][y] -= 2;
                    }
                }
            }
        }

        int totalWater = 0;
        for (int[] baskets : map) {
            for (int water : baskets) {
                totalWater += water;
            }
        }
        System.out.println(totalWater);
    }
}
