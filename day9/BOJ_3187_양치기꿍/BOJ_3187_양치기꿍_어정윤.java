import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_양치기꿍_어정윤 {

    private static final int[][] DELTAS = {{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final char FENCE = '#';
    private static final char WOLF = 'v';
    private static final char SHEEP = 'k';
    private static final char VISIT = 'x';

    private static int r;
    private static int c;
    private static int wolves;
    private static int sheep;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        r = Integer.parseInt(stringTokenizer.nextToken());
        c = Integer.parseInt(stringTokenizer.nextToken());
        char[][] space = new char[r][c];
        for (int i = 0; i < r; i++) {
            String inputLine = bufferedReader.readLine();
            for (int j = 0; j < c; j++) {
                char input = inputLine.charAt(j);
                if (input == WOLF) {
                    wolves++;
                } else if (input == SHEEP) {
                    sheep++;
                }
                space[i][j] = input;
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (space[i][j] != FENCE && space[i][j] != VISIT) {
                    searchArea(space, i, j);
                }
            }
        }

        stringBuilder.append(sheep)
                .append(" ")
                .append(wolves);
        System.out.println(stringBuilder);
    }

    private static void searchArea(char[][] space, int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        int wolfCnt = 0;
        int sheepCnt = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < r && dy >= 0 && dy < c && space[dx][dy] != FENCE && space[dx][dy] != VISIT) {
                    if (space[dx][dy] == WOLF) {
                        wolfCnt++;
                    } else if (space[dx][dy] == SHEEP) {
                        sheepCnt++;
                    }
                    space[dx][dy] = VISIT;
                    queue.offer(new int[]{dx, dy});
                }
            }
        }

        if (wolfCnt < sheepCnt) {
            wolves -= wolfCnt;
        } else {
            sheep -= sheepCnt;
        }
    }
}
