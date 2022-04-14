import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15684_사다리조작_어정윤 {

    private static final int[] DELTAS = {-1, 1};
    private static final List<int[]> BRIDGE_CANDIDATES = new ArrayList<>();

    private static int n;
    private static int h;
    private static boolean[][] isBridge;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        h = Integer.parseInt(stringTokenizer.nextToken());
        isBridge = new boolean[h + 1][n + 1];
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int horizontal = Integer.parseInt(stringTokenizer.nextToken());
            int vertical = Integer.parseInt(stringTokenizer.nextToken());
            isBridge[horizontal][vertical] = true;
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                if (!isBridge[i][j] && canInstallNewBridge(i, j)) {
                    BRIDGE_CANDIDATES.add(new int[]{i, j});
                }
            }
        }

        for (int bridgeNumber = 0; bridgeNumber <= 3; bridgeNumber++) {
            installBridge(bridgeNumber, 0, 0);
        }
        System.out.println(-1);
    }

    private static void installBridge(int bridgeNumber, int cnt, int start) {
        if (cnt == bridgeNumber) {
            if (isCorrectBridge()) {
                System.out.println(cnt);
                System.exit(0);
            }
            return;
        }

        for (int i = start; i < BRIDGE_CANDIDATES.size(); i++) {
            int[] bridgeCandidate = BRIDGE_CANDIDATES.get(i);
            int x = bridgeCandidate[0];
            int y = bridgeCandidate[1];
            if (canInstallNewBridge(x, y)) {
                isBridge[x][y] = true;
                installBridge(bridgeNumber, cnt + 1, i + 1);
                isBridge[x][y] = false;
            }
        }
    }

    private static boolean canInstallNewBridge(int horizontal, int vertical) {
        for (int delta : DELTAS) {
            int adjacentVerticalLine = vertical + delta;
            if (adjacentVerticalLine > 0 && adjacentVerticalLine <= n && isBridge[horizontal][adjacentVerticalLine]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isCorrectBridge() {
        for (int startVertical = 1; startVertical <= n; startVertical++) {
            int vertical = startVertical;
            int horizontal = 1;
            while (horizontal <= h) {
                if (vertical <= n && isBridge[horizontal][vertical]) {
                    vertical++;
                } else if (vertical > 1 && isBridge[horizontal][vertical - 1]) {
                    vertical--;
                }
                horizontal++;
            }

            if (vertical != startVertical) {
                return false;
            }
        }
        return true;
    }
}
