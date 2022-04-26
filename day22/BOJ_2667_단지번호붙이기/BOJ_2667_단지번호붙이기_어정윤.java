import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2667_단지번호붙이기_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) - 48;
            }
        }

        int totalApt = 0;
        List<Integer> houses = new ArrayList<>();
        boolean[][] isVisited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !isVisited[i][j]) {
                    totalApt++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    int house = 0;
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int x = current[0];
                        int y = current[1];
                        isVisited[x][y] = true;
                        house++;
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n && map[dx][dy] == 1 && !isVisited[dx][dy]) {
                                isVisited[dx][dy] = true;
                                queue.offer(new int[]{dx, dy});
                            }
                        }
                    }
                    houses.add(house);
                }
            }
        }
        houses.sort(Comparator.naturalOrder());
        stringBuilder.append(totalApt)
                .append("\n");
        for (int house : houses) {
            stringBuilder.append(house)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }
}
