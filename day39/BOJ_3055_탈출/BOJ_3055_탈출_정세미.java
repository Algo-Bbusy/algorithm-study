import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 24분, 16276KB, 164ms
public class Main_탈출_3055 {
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int start = 0;
        int water = 0;
        boolean[][] visited = new boolean[R][C];
        boolean[][] waterVisited = new boolean[R][C];
        Queue<Integer> waterQueue = new ArrayDeque<>();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);
                if(c == 'S') {
                    start = i * C + j;
                    q.offer(start);
                    visited[i][j] = true;
                    c = '.';
                } else if(c == '*') {
                    water = i * C + j;
                    waterVisited[i][j] = true;
                    waterQueue.offer(water);
                }
                map[i][j] = c;
            }
        }
        int step = 1;
        while(!q.isEmpty()) {
            int ws = waterQueue.size();
            while(ws-- > 0) {
                int cur = waterQueue.poll();
                int r = cur / C;
                int c = cur % C;

                for (int i = 0; i < 4; i++) {
                    int nr = r + d[i][0];
                    int nc = c + d[i][1];

                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || waterVisited[nr][nc] || map[nr][nc] != '.') {
                        continue;
                    }
                    waterVisited[nr][nc] = true;
                    map[nr][nc] = '*';
                    waterQueue.offer(nr * C + nc);
                }
            }
            int s = q.size();
            while(s-- > 0) {
                int cur = q.poll();
                int r = cur / C;
                int c = cur % C;

                for (int i = 0; i < 4; i++) {
                    int nr = r + d[i][0];
                    int nc = c + d[i][1];

                    if(nr < 0 || nr >= R || nc < 0 || nc >= C || waterVisited[nr][nc] || visited[nr][nc] || map[nr][nc] == 'X' || map[nr][nc] == '*') {
                        continue;
                    }

                    if(map[nr][nc] == 'D') {
                        System.out.println(step);
                        System.exit(0);
                    }
                    visited[nr][nc] = true;
                    q.offer(nr * C + nc);
                }
            }
            ++step;
        }
        System.out.println("KAKTUS");
    }
}