import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final boolean[][] IS_VISITED = new boolean[HEIGHT+1][WIDTH+1];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int n = Integer.parseInt(bufferedReader.readLine());
        List<Integer> moveDirections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int generation = Integer.parseInt(stringTokenizer.nextToken());
            moveDirections.clear();
            moveDirections.add(direction);
            IS_VISITED[y][x] = true;
            y += DELTAS[direction][0];
            x += DELTAS[direction][1];
            IS_VISITED[y][x] = true;
            for (int j = 0; j < generation; j++) {
                int moveStart = moveDirections.size();
                for (int previousMove = moveStart - 1; previousMove >= 0; previousMove--) {
                    moveDirections.add((moveDirections.get(previousMove) + 1) % 4);
                }
                for (int k = moveStart, moveDirectionsSize = moveDirections.size(); k < moveDirectionsSize; k++) {
                    int moveDirection = moveDirections.get(k);
                    y += DELTAS[moveDirection][0];
                    x += DELTAS[moveDirection][1];
                    IS_VISITED[y][x] = true;
                }
            }
        }
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        int cnt = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (IS_VISITED[i][j] && IS_VISITED[i][j + 1] && IS_VISITED[i + 1][j] && IS_VISITED[i + 1][j + 1]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
