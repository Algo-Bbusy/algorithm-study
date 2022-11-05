import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2234_성곽_정세미 {
    static int N, M, num, maxSize, maxAfterDeletion;

    static List<Integer> size = new ArrayList<>();
    static int[][] map;
    static int[][] mark;
    static int[][] d = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        size.add(-1);
        map = new int[N][M];
        mark = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        find();
        delete();
        System.out.println(num + "\n" + maxSize + "\n" + maxAfterDeletion);
    }

    private static void delete() {
        boolean[] visited = new boolean[size.size()];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int n = mark[i][j];
                if (!visited[n]) {
                    visited[n] = true;
                    deleteBfs(n, i, j);
                }
            }
        }
    }

    private static void deleteBfs(int n, int x, int y) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(x * M + y);
        Set<Integer> adjacentSize = new HashSet<>();
        boolean[][] visited = new boolean[N][M];
        while (!q.isEmpty()) {
            int c = q.poll();
            x = c / M;
            y = c % M;

            for (int i = 0; i < 4; ++i) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                    continue;
                }
                if ((map[x][y] & 1 << i) != 0) {
                    if (mark[nx][ny] != n) {
                        adjacentSize.add(size.get(mark[nx][ny]));
                    }
                } else {
                    visited[nx][ny] = true;
                    q.offer(nx * M + ny);
                }
            }
        }
        adjacentSize.forEach(s -> {
            maxAfterDeletion = Math.max(maxAfterDeletion, s + size.get(n));
        });
    }

    static void find() {
        int n = 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (mark[i][j] == 0) {
                    maxSize = Math.max(fillBfs(n++, i, j), maxSize);
                }
            }
        }
        num = n - 1;
    }

    static int fillBfs(int n, int x, int y) {
        int ret = 1;
        mark[x][y] = n;
        Queue<Integer> q = new LinkedList<>();
        q.offer(x * M + y);
        while (!q.isEmpty()) {
            int c = q.poll();
            x = c / M;
            y = c % M;
            for (int i = 0; i < 4; ++i) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && ((map[x][y] & 1 << i) == 0) && mark[nx][ny] == 0) {
                    mark[nx][ny] = n;
                    ret++;
                    q.offer(nx * M + ny);
                }
            }
        }
        size.add(ret);
        return ret;
    }
}
