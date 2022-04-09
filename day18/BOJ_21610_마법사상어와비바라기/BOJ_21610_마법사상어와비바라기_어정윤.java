import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기_어정윤 {

    private static int n;
    private static int[][] deltas;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        deltas = new int[][]{{0, 0}, {0, n - 1}, {n - 1, n - 1}, {n - 1, 0}, {n - 1, 1},
                {0, 1}, {1, 1}, {1, 0}, {1, n - 1}};
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{n, 1});
        clouds.add(new int[]{n, 2});
        clouds.add(new int[]{n - 1, 1});
        clouds.add(new int[]{n - 1, 2});
        for (int i = 0; i < m; i++) {
            boolean[][] wasThereCloud = new boolean[n][n];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int moveNum = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println("시작");
            for (int[] ints : map) {
                System.out.println(Arrays.toString(ints));
            }
            for (int j = 0; j < clouds.size(); j++) {
                // 1. 모든 구름이 di 방향으로 si칸 이동
                for (int k = 0; k < moveNum; k++) {
                    clouds.get(j)[0] = (clouds.get(j)[0]+deltas[direction][0]) % n;
//                    if (DELTAS[direction][0] != 0) {
//                        clouds.get(j)[0] %= n;
//                        if (clouds.get(j)[0] == 0) {
//                            clouds.get(j)[0] = n;
//                        }
//                    }
                    clouds.get(j)[1] = (clouds.get(j)[1] + deltas[direction][1]) % n;
//                    if (DELTAS[direction][1] != 0) {
//                        clouds.get(j)[1] %= n;
//                        if (clouds.get(j)[1] == 0) {
//                            clouds.get(j)[1] = n;
//                        }
//                    }
                    System.out.println(clouds.get(j)[0]+", "+clouds.get(j)[1]);
                }
//                System.out.println(clouds.get(j)[0]+", "+clouds.get(j)[1]);
            }
            for (int[] cloud : clouds) {
                // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양 1 증가
                map[cloud[0]][cloud[1]]++;
                // 3. 구름이 사라짐
                wasThereCloud[cloud[0]][cloud[1]] = true;
            }
            System.out.println(i + 1);
            for (boolean[] booleans : wasThereCloud) {
                System.out.println(Arrays.toString(booleans));
            }
            System.out.println("바구니");
            for (int[] ints : map) {
                System.out.println(Arrays.toString(ints));
            }

            // 4. 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물의 양 증가
//            for (int[] cloud : clouds) {
//                System.out.println(Arrays.toString(cloud));
//            }
            for (int j = 0; j < clouds.size(); j++) {
                int x = clouds.get(j)[0];
                int y = clouds.get(j)[1];
                int basketWithWater = 0;
                System.out.println("=================");
                System.out.println("x: " + x + ", y:" + y);
                for (int k = 2; k < deltas.length; k += 2) {
//                    int dx = (x + DELTAS[k][0] == 0) ? n : x + DELTAS[k][0];
//                    int dy = (y + DELTAS[k][1] == 0) ? n : y + DELTAS[k][1];
                    int dx = x + deltas[k][0];
                    int dy = y + deltas[k][1];
                    System.out.println(Arrays.toString(deltas[k]) + dx + ", " + dy);
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && map[dx][dy] != 0) {
                        basketWithWater++;
                    }
                }
                map[x][y] += basketWithWater;
            }
            System.out.println("4번 후 바구니");
            for (int[] ints : map) {
                System.out.println(Arrays.toString(ints));
            }

            clouds.clear();
            System.out.println("구름===========");
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[j][k] >= 2 && !wasThereCloud[j][k]) {
                        clouds.add(new int[]{j, k});
                        map[j][k] -= 2;
                    }
                }
            }
            for (int[] cloud : clouds) {
                System.out.println(Arrays.toString(cloud));
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
