import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16918_봄버맨_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final char BOMB = 'O';
    private static final char EMPTY = '.';
    private static final int INSTALLED_TIME = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int r = Integer.parseInt(stringTokenizer.nextToken());
        int c = Integer.parseInt(stringTokenizer.nextToken());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[r][c];
        Queue<int[]> bombs = new LinkedList<>();
        int time = 0;
        for (int i = 0; i < r; i++) {
            String inputLine = bufferedReader.readLine();
            for (int j = 0; j < c; j++) {
                char input = inputLine.charAt(j);
                map[i][j] = input;
                if (input == BOMB) {
                    bombs.offer(new int[]{i, j, time});
                }
            }
        }

        time++;
        while (++time <= n) {
            if (time % 2 == 0) {
                installBomb(map, r, c, time, bombs);
            } else {
                explode(map, r, c, time, bombs);
            }
        }
        print(r, c, map);
    }

    private static void explode(char[][] map, int r, int c, int time, Queue<int[]> bombs) {
        while (time - bombs.peek()[INSTALLED_TIME] == 3) {
            int[] bomb = bombs.poll();
            int x = bomb[0];
            int y = bomb[1];
            map[x][y] = EMPTY;
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < r && dy >= 0 && dy < c && map[dx][dy] == BOMB) {
                    map[dx][dy] = EMPTY;
                }
            }
        }
    }

    private static void installBomb(char[][] map, int r, int c, int time, Queue<int[]> bombs) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == EMPTY) {
                    map[i][j] = BOMB;
                    bombs.offer(new int[]{i, j, time});
                }
            }
        }
    }

    private static void print(int r, int c, char[][] map) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                answer.append(map[i][j]);
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}
