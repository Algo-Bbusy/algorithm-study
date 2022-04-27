import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23288_주사위굴리기2_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};   // 동남서북
    private static final int EAST = 0;
    private static final int SOUTH = 1;
    private static final int WEST = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int[] dice = {1, 3, 6, 4, 5, 2};
        int score = 0;
        int direction = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < k; i++) {
            x += DELTAS[direction][0];
            y += DELTAS[direction][1];
            if (x < 0 || x >= n || y < 0 || y >= m) {   // 경계 벗어나면 이동방향 반대로 굴림
                direction = (direction + 2) % 4;
                x += DELTAS[direction][0] * 2;
                y += DELTAS[direction][1] * 2;
            }

            int top = 0;
            int east = 1;
            int bottom = 2;
            int west = 3;
            int front = 4;
            int back = 5;
            if (direction == EAST) {
                top = 1;
                east = 2;
                bottom = 3;
                west = 0;
            } else if (direction == WEST) {
                top = 3;
                east = 0;
                bottom = 1;
                west = 2;
            } else if (direction == SOUTH) {
                top = 4;
                bottom = 5;
                front = 2;
                back = 0;
            } else {
                top = 5;
                bottom = 4;
                front = 0;
                back = 2;
            }

            int[] tempDice = new int[6];
            tempDice[top] = dice[0];
            tempDice[east] = dice[1];
            tempDice[bottom] = dice[2];
            tempDice[west] = dice[3];
            tempDice[front] = dice[4];
            tempDice[back] = dice[5];
            dice = tempDice;

            int A = dice[2];
            int B = map[x][y];
            int C = 0;

            Queue<int[]> queue = new LinkedList<>();
            boolean[][] isVisited = new boolean[n][m];
            queue.offer(new int[]{x, y});
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];
                for (int[] delta : DELTAS) {
                    int dr = r + delta[0];
                    int dc = c + delta[1];
                    if (dr >= 0 && dr < n && dc >= 0 && dc < m && !isVisited[dr][dc] && map[dr][dc] == B) {
                        C++;
                        isVisited[dr][dc] = true;
                        queue.offer(new int[]{dr, dc});
                    }
                }
            }

            score += B * (C == 0 ? 1 : C);
            if (A > B) {
                direction = (direction + 1) % 4;
            } else if (A < B) {
                direction = (direction + 3) % 4;
            }
        }
        System.out.println(score);
    }
}
