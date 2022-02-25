import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10157_자리배정_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int r;
    private static int c;
    private static int k;
    private static int waitingNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        c = Integer.parseInt(stringTokenizer.nextToken());
        r = Integer.parseInt(stringTokenizer.nextToken());
        k = Integer.parseInt(bufferedReader.readLine());

        if (k > r * c) {
            System.out.println(0);
            return;
        }

        waitingNumber = 1;
        int x = 1;
        int y = 1;
        int edge = r * 2 + c * 2 - 4;
        while (edge < k) {
            r -= 2;
            c -= 2;
            x++;
            y++;
            waitingNumber = edge+1;
            edge += r * 2 + c * 2 - 4;
        }

        for (int i = 0, deltasLength = DELTAS.length; i < deltasLength; i++) {
            int[] delta = DELTAS[i];
            int dx = x + delta[0];
            int dy = y + delta[1];
            while (canGo(i, dx, x, dy, y)) {
                dx += delta[0];
                dy += delta[1];
            }
            x = dx - delta[0];
            y = dy - delta[1];
            if (waitingNumber - 1 == k) {
                break;
            }
        }
        System.out.println(x + " " + y);
    }

    private static boolean canGo(int direction, int dx, int x, int dy, int y) {
        if (direction < 2) {
            return dx >= x && dx < x + c && dy >= y && dy < y + r && waitingNumber++ != k;
        } else {
            return dx <= x && dx > x - c && dy <= y && dy > y - r && waitingNumber++ != k;
        }
    }
}
