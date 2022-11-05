package anystep;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Java_2234 {
    //성곽
//메모리 146416KB 시간 372ms
//문제에서 서 북 남 동 순으로 벽이 존재할 시 1 2 4 8 을 더한 값을 의미한다.(비트마스킹 유추)
//bfs를 통하여 bfs가 끝마칠때마다 방의 개수를 구분할 수 있다.
//벽을 허물기 전과 허문 후의 두단계로 나누어서 최대 넓이 값을 비교한다.
//bfs 매 실행시마다 해당 방의 넓이를 최대 방 넓이와 비교한다.
//Solution 1.
//1. bfs 탐색을 위한 map과 방문여부 배열의 선언 및 초기값 입력
//2. 벽을 허물기 전 방의 개수와 최대 방 넓이를 구한다.
//2.1. 해당 맵 좌표를 방문하지 않았다면 bfs 실행
//2.2. 가고자 하는 방향과 바라보고 있는 벽의 방향(서,북,동,남 순)으로 bfs 탐색을 진행한다.
//2.3. 해당 방향의 탐색이 끝날 시 진행방향과 벽의방향을 바꾼다.
//2.4. bfs 탐색 사이클이 끝나면 해당 방의 넓이를 반환한다.
//3. 벽을 허문 후 최대 방 넓이를 구한다.
//3.1. (서,북,동,남 순)으로 해당 좌표와 벽의 방향의 & 연산시 벽이 있다면 벽을 제거후 bfs 진행
//3.2. bfs 종료시 해당 좌표의 벽을 복구.
//풀이시간 2시간 1분 42초
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer stk;
    static int N, M, roomCnt, maxArea, finalMaxArea;
    static int[][] map;
    static boolean[][] isVisted;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        input();
        defaultRoomSearch();
        breakWallRoomSearch();
        printAns();
    }

    private static void printAns() throws IOException {
        out.write(sb.toString());
        out.flush();
        out.close();
        in.close();
    }

    private static void breakWallRoomSearch() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                for (int wallDir = 1; wallDir <= 8; wallDir <<= 1) {
                    if ((map[y][x] & wallDir) != 0) {
                        isVisted = new boolean[N][M];
                        map[y][x] -= wallDir;
                        finalMaxArea = Math.max(finalMaxArea, bfs(y, x));
                        map[y][x] += wallDir;
                    }
                }
            }
        }
        sb.append(finalMaxArea);
    }

    private static void defaultRoomSearch() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (!isVisted[y][x]) {
                    maxArea = Math.max(maxArea, bfs(y, x));
                    roomCnt++;
                }
            }
        }
        sb.append(roomCnt).append("\n").append(maxArea).append("\n");
    }

    private static int bfs(int y, int x) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        isVisted[y][x] = true;
        int currentArea = 1;
        while (!q.isEmpty()) {
            Point now = q.poll();
            int wallDir = 1;
            for (int d = 0; d < 4; d++) {
                if ((map[now.y][now.x] & wallDir) == 0) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    if (!(ny >= 0 && ny < N && nx >= 0 && nx < M)) continue;
                    if (ny >= 0 && ny < N && nx >= 0 && nx < M && !isVisted[ny][nx]) {
                        currentArea++;
                        isVisted[ny][nx] = true;
                        q.add(new Point(nx, ny));
                    }
                }
                wallDir <<= 1;
            }
        }
        return currentArea;
    }

    private static void input() throws IOException {
        stk = new StringTokenizer(in.readLine());
        M = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        map = new int[N][M];
        isVisted = new boolean[N][M];
        for (int y = 0; y < N; y++) {
            stk = new StringTokenizer(in.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(stk.nextToken());
            }
        }
    }
}
