import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_뱀_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final String LEFT = "L";
    private static final int CUT = 0;
    private static final int APPLE = 1;
    private static final int SNAKE = 2;

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int appleNum = Integer.parseInt(bufferedReader.readLine());
        int[][] board = new int[n + 1][n + 1];
        Queue<int[]> snake = new LinkedList<>();
        board[1][1] = SNAKE;
        snake.offer(new int[]{1, 1});
        for (int i = 0; i < appleNum; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            board[x][y] = APPLE;
        }
        int directionChangeNum = Integer.parseInt(bufferedReader.readLine());
        Queue<String[]> directionChangeInfos = new LinkedList<>();
        for (int i = 0; i < directionChangeNum; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            directionChangeInfos.offer(new String[]{stringTokenizer.nextToken(), stringTokenizer.nextToken()});
        }
        play(directionChangeInfos, board, snake, n);
    }

    private static void play(Queue<String[]> directionChangeInfos, int[][] board, Queue<int[]> snake, int n) {
        String[] nextTurn = directionChangeInfos.poll();
        int nextTurnTime = Integer.parseInt(nextTurn[0]);
        String nextTurnDirection = nextTurn[1];

        int direction = 0;
        int dx = 1 + DELTAS[direction][0];
        int dy = 1 + DELTAS[direction][1];
        int time = 1;
        while (dx > 0 && dx <= n && dy > 0 && dy <= n && board[dx][dy] != SNAKE) {
            if (time == nextTurnTime) {
                if (nextTurnDirection.equals(LEFT)) {
                    direction = (direction + 3) %  DELTAS.length;
                } else {
                    direction = (direction + 1) % DELTAS.length;
                }
                if (!directionChangeInfos.isEmpty()){
                    nextTurn = directionChangeInfos.poll();
                    nextTurnTime = Integer.parseInt(nextTurn[0]);
                    nextTurnDirection = nextTurn[1];
                }
            }
            time++;
            int current = board[dx][dy];
            board[dx][dy] = SNAKE;
            snake.offer(new int[]{dx, dy});
            if (current != APPLE) {
                int[] snakeTail = snake.poll();
                board[snakeTail[0]][snakeTail[1]] = CUT;
            }
            dx += DELTAS[direction][0];
            dy += DELTAS[direction][1];
        }
        System.out.println(time);
    }
}
