import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇_어정윤 {

    private static int[] conveyor;
    private static boolean[] hasRobot;
    private static int n;
    private static int zeroDurability;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        conveyor = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            conveyor[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        hasRobot = new boolean[n];
        zeroDurability = 0;
        int step = 0;
        while (true) {
            step++;
            rotate();
            moveRobots();
            if (--conveyor[0] >= 0) {
                hasRobot[0] = true;
                if (conveyor[0] == 0) {
                    zeroDurability++;
                }
            }
            if (zeroDurability >= k) {
                break;
            }
        }
        System.out.println(step);
    }

    private static void rotate() {
        int lastDurability = conveyor[2*n-1];
        for (int i = 2*n-1; i > 0; i--) {
            conveyor[i] = conveyor[i-1];
            if (i < n) {
                hasRobot[i] = hasRobot[i-1];
            }
        }

        if (hasRobot[n-1]) {
            hasRobot[n-1] = false;
        }

        conveyor[0] = lastDurability;
        hasRobot[0] = false;
    }

    private static void moveRobots() {
        for (int i = n-1; i > 0; i--) {
            if (hasRobot[i-1] && !hasRobot[i] && conveyor[i] > 0) {
                hasRobot[i] = true;
                hasRobot[i-1] = false;
                if (--conveyor[i] == 0) {
                    zeroDurability++;
                }
            }
        }

        if (hasRobot[n-1]) {
            hasRobot[n-1] = false;
        }
    }
}
