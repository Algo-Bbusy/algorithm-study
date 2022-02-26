import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10158_개미_어정윤 {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final int[] HORIZONTALS = {-1, 1}; // 좌우
    private static final int[] VERTICALS = {1, -1}; // 상하
    private static final int RIGHT = 1;
    private static final int UP = 0;

    private static int w;
    private static int h;
    private static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        w = Integer.parseInt(stringTokenizer.nextToken());
        h = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int p = Integer.parseInt(stringTokenizer.nextToken());
        int q = Integer.parseInt(stringTokenizer.nextToken());
        t = Integer.parseInt(bufferedReader.readLine());
        move(0, p, q, RIGHT, UP);
        System.out.println(STRING_BUILDER);
    }

    private static void move(int time, int p, int q, int horizontal, int vertical) {
        if (time == t) {
            STRING_BUILDER.append(p)
                    .append(" ")
                    .append(q);
            return;
        }

        if (p == 0 || p == w) {
            horizontal = (horizontal+1) % 2;
        }
        if (q == 0 || q == h) {
            vertical = (vertical+1) % 2;
        }

        p += HORIZONTALS[horizontal];
        q += VERTICALS[vertical];

        move(time+1, p, q, horizontal, vertical);
    }
}
