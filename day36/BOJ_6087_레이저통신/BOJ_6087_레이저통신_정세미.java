import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6087_레이저통신 {
    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static class Position {
        int x;
        int y;
        int mirrorCount;
        int previousDirection;

        public Position(int x, int y, int mirrorCount, int previousDirection) {
            this.x = x;
            this.y = y;
            this.mirrorCount = mirrorCount;
            this.previousDirection = previousDirection;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];

        Position start = null;
        for (int i = 0; i < H; i++) {
            String string = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = string.charAt(j);
                if (map[i][j] == 'C' && start == null) {
                    start = new Position(i, j, 0, -1);
                }
            }
        }

        int[][] visited = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        Queue<Position> q = new ArrayDeque<>();
        visited[start.x][start.y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = start.x + d[i][0];
            int ny = start.y + d[i][1];

            if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '*' || visited[nx][ny] < 0) {
                continue;
            }

            visited[nx][ny] = 0;
            q.offer(new Position(nx, ny, 0, i));
        }
        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Position current = q.poll();

            if (map[current.x][current.y] == 'C') {
                answer = Math.min(answer, current.mirrorCount);
            }

            for (int i = -1; i <= 1; i++) {
                int di = (current.previousDirection + i + 4) % 4;
                int nx = current.x + d[di][0];
                int ny = current.y + d[di][1];

                int mirrorCount = current.mirrorCount + Math.abs(i);
                if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '*' || visited[nx][ny] < mirrorCount) {
                    continue;
                }

                visited[nx][ny] = mirrorCount;
                q.offer(new Position(nx, ny, mirrorCount, di));
            }
        }
        System.out.println(answer);
    }
}
