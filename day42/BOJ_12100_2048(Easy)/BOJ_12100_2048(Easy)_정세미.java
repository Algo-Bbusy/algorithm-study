import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2048_easy {
    static final int MAX_MOVE = 5;
    static int N, answer, maxSameCount, total;
    static int[][] map;
    static int[] d = {1, 1, -1, -1}; // 하 우 상 좌
    static int[] start;

    public static void main(String[] args) throws Exception {
        input();
        process(map, 0, 0, -1);
        System.out.println(answer);
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        start = new int[]{0, 0, N - 1, N - 1};
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    ++total;
                }
                answer = Math.max(answer, map[i][j]);
            }
        }
        maxSameCount = (int) (Math.log(Math.min(total, N == 0 ? 1 : N)) / Math.log(2));
    }

    private static void process(int[][] map, int cnt, int prevDir, int sameCount) {
        if (cnt == MAX_MOVE || sameCount > maxSameCount || total == 1) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int tempTotal = total;
            int[][] newMap = move(map, i);
            if (compare(newMap, map)) {
                continue;
            }
            process(newMap, cnt + 1, i, prevDir == i ? sameCount + 1 : 0);
            total = tempTotal;
        }
    }

    private static boolean compare(int[][] newMap, int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (newMap[i][j] != map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] move(int[][] oldMap, int dirIdx) {
        int[][] map = new int[N][];
        for (int j = 0; j < N; j++) {
            map[j] = oldMap[j].clone();
        }

        int s = start[dirIdx];
        int di = d[dirIdx];
        boolean isVertical = dirIdx % 2 == 0;

        for (int i = 0; i < N; i++) {
            int dest = s;
            int src = s + di;
            while (check(src, N - s - 1)) {
                if (getValue(isVertical, map, i, src) != 0) {
                    if (getValue(isVertical, map, i, dest) == 0) {
                        moveSrcToDest(isVertical, map, i, src, dest);
                    } else if (getValue(isVertical, map, i, src) == getValue(isVertical, map, i, dest)) {
                        moveSrcToDest(isVertical, map, i, src, dest);
                        dest += di;
                        --total;
                    } else {
                        dest += di;
                        if (src != dest) {
                            continue;
                        }
                    }
                }
                src += di;
            }
        }

        return map;
    }

    private static void moveSrcToDest(boolean isVertical, int[][] map, int i, int src, int dest) {
        if (isVertical) {
            map[dest][i] += map[src][i];
            answer = Math.max(map[dest][i], answer);
            map[src][i] = 0;
        } else {
            map[i][dest] += map[i][src];
            answer = Math.max(map[i][dest], answer);
            map[i][src] = 0;
        }
    }

    private static int getValue(boolean isVertical, int[][] map, int fixedIdx, int idx) {
        return (isVertical ? map[idx][fixedIdx] : map[fixedIdx][idx]);
    }

    private static boolean check(int src, int s) {
        return s == 0 ? src >= s : src <= s;
    }
}
