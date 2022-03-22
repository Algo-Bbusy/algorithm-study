import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16234_인구이동_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int l = Integer.parseInt(stringTokenizer.nextToken());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int[][] land = new int[n][n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                land[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        List<int[]> closeCountry = new ArrayList<>();
        System.out.println(migrate(n, l, r, land, closeCountry));
    }

    private static int getMigrationDay(int n, int l, int r, int[][] land, List<int[]> closeCountry) {
        int day = 0;
        boolean canMigrate = false;
        boolean[][] isVisited;
        while (true) {
            int[][] copyLand = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    copyLand[i][j] = land[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    isVisited = new boolean[n][n];
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] poll = queue.poll();
                        int x = poll[0];
                        int y = poll[1];
                        isVisited[x][y] = true;
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n && !isVisited[dx][dy]){
                                int populationGap = Math.abs(land[x][y] - land[dx][dy]);
                                if(populationGap >= l && populationGap <= r) {
                                    canMigrate = true;
                                    isVisited[dx][dy] = true;
                                    int[] next = {dx, dy};
                                    queue.offer(next);
                                    if (!closeCountry.contains(next)) {
                                        closeCountry.add(next);
                                    }
                                }
                            }
                        }
                    }
                    if (!closeCountry.isEmpty()) {
                        closeCountry.add(new int[]{i, j});
                        int sum = closeCountry.stream()
                                .mapToInt(location -> copyLand[location[0]][location[1]])
                                .sum();
                        int closeCountryCount = closeCountry.size();
                        closeCountry.forEach(location -> copyLand[location[0]][location[1]] = sum / closeCountryCount);
                        closeCountry.clear();
                    }
                }
            }
            land = copyLand;
            if (!canMigrate) {
                return day;
            }
            day++;
        }
    }
}
