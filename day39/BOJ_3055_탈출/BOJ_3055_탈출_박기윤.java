package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_3055_탈출 {

    static final char EMPTY = '.';
    static final char WATER = '*';
    static final char STONE = 'X';
    static final char BEAVER = 'D';
    static final char HEDGEHOG = 'S';
    static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static int R, C;
    static char[][] map;
    static boolean[][] isVisited;
    static boolean[][] isSinked;
    static Queue<Node> queue;

    public static class Node {
        int r;
        int c;
        int time;
        boolean isHedgehog;

        public Node(int r, int c, int time, boolean isHedgehog) {
            super();
            this.r = r;
            this.c = c;
            this.time = time;
            this.isHedgehog = isHedgehog;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        isVisited = new boolean[R][C];
        isSinked = new boolean[R][C];
        queue = new LinkedList<>();
        Node start = null;
        String line;
        for (int i = 0; i < R; i++) {
            line = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                if (c == HEDGEHOG) {
                    start = new Node(i, j, 0, true);
                    isVisited[i][j] = true;
                } else if (c == WATER) {
                    queue.offer(new Node(i, j, 0, false));
                    isSinked[i][j] = true;
                }
            }
        }
        int answer = move(start);
        if (answer == -1) {
            bw.write("KAKTUS");
        } else {
            bw.write(Integer.toString(answer));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    private static int move(Node start) {
        queue.offer(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int r = node.r;
            int c = node.c;
            int time = node.time;
            boolean isHedgehog = node.isHedgehog;
            if (isHedgehog && map[r][c] == BEAVER) {
                return time;
            }
            if (isHedgehog && isSinked[r][c]) {
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nr = r + DELTAS[d][0];
                int nc = c + DELTAS[d][1];
                if (!isIn(nr, nc) || map[nr][nc] == STONE || isSinked[nr][nc]) {
                    continue;
                }
                if (isHedgehog && isVisited[nr][nc]) {
                    continue;
                } else if (!isHedgehog && map[nr][nc] == BEAVER) {
                    continue;
                }
                queue.offer(new Node(nr, nc, time + 1, isHedgehog));
                isVisited[nr][nc] = true;
                if (!isHedgehog) {
                    isSinked[nr][nc] = true;
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
