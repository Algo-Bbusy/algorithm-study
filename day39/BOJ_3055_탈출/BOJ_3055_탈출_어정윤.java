import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055_탈출_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final char EMPTY = '.';
    private static final char WATER = '*';
    private static final char STONE = 'X';
    private static final char BEAVER = 'D';
    private static final char HEDGEHOG = 'S';

    private static int goalX;
    private static int goalY;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[r][c];
        Queue<int[]> hedgehog = new ArrayDeque<>();
        Queue<int[]> water = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            String inputLine = bufferedReader.readLine();
            for (int j = 0; j < c; j++) {
                char input = inputLine.charAt(j);
                map[i][j] = input;
                if (input == HEDGEHOG) {
                    hedgehog.offer(new int[]{i, j});
                } else if (input == WATER) {
                    water.offer(new int[]{i, j});
                } else if (input == BEAVER) {
                    goalX = i;
                    goalY = j;
                }
            }
        }

        int[][] visited = new int[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(visited[i], r * c);
        }

        boolean[][] isSpread = new boolean[r][c];
        int minTime = r * c;
        int time = 0;
        while (!hedgehog.isEmpty()) {
            spreadWater(r, c, map, water, isSpread);
            int size = hedgehog.size();
            while (size-- > 0) {
                int[] current = hedgehog.poll();
                int x = current[0];
                int y = current[1];
                if (x == goalX && y == goalY) {
                    minTime = Math.min(minTime, time);
                }

                map[x][y] = EMPTY;
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < r && dy >= 0 && dy < c && map[dx][dy] != WATER && map[dx][dy] != STONE) {
                        if (visited[dx][dy] > time + 1) {
                            visited[dx][dy] = time + 1;
                            map[dx][dy] = HEDGEHOG;
                            hedgehog.offer(new int[]{dx, dy});
                        }
                    }
                }
            }
            time++;
        }

        if (minTime == r * c) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(minTime);
        }
    }

    private static void spreadWater(int r, int c, char[][] map, Queue<int[]> water, boolean[][] isSpread) {
        int size = water.size();
        while (size-- > 0) {
            int[] currentWater = water.poll();
            for (int[] delta : DELTAS) {
                int dx = currentWater[0] + delta[0];
                int dy = currentWater[1] + delta[1];
                if (dx >= 0 && dx < r && dy >= 0 && dy < c && !isSpread[dx][dy] && map[dx][dy] != BEAVER && map[dx][dy] != STONE) {
                    map[dx][dy] = WATER;
                    isSpread[dx][dy] = true;
                    water.offer(new int[]{dx, dy});
                }
            }
        }
    }
}
