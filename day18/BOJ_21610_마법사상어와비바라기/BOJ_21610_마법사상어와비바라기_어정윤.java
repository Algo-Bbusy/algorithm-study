import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{n - 1, 0});
        clouds.add(new int[]{n - 1, 1});
        clouds.add(new int[]{n - 2, 0});
        clouds.add(new int[]{n - 2, 1});

        for (int i = 0; i < m; i++) {
            boolean[][] wasThereCloud = new boolean[n][n];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int moveNum = Integer.parseInt(stringTokenizer.nextToken());
            for (int[] cloud : clouds) {
                // 1. 모든 구름이 di 방향으로 si칸 이동
                cloud[0] = (cloud[0] + deltas[direction][0] * moveNum) % n;
                cloud[1] = (cloud[1] + deltas[direction][1] * moveNum) % n;
                // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양 1 증가
                map[cloud[0]][cloud[1]]++;
                // 3. 구름이 사라짐
                wasThereCloud[cloud[0]][cloud[1]] = true;
            }

            // 4. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물의 양 증가
            for (int[] cloud : clouds) {
                int x = cloud[0];
                int y = cloud[1];
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

            // 5. 새로운 구름 생성
            clouds.clear();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[j][k] >= 2 && !wasThereCloud[j][k]) {
                        clouds.add(new int[]{j, k});
                        map[j][k] -= 2;
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
