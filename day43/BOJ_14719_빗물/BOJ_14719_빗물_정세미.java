import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719_빗물 {
    static int W;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] map = new int[W];
        for (int i = 0; i < W; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int cur = 0;
        while (cur < W) {
            int l = find(map, cur, -1);
            int r = find(map, cur, 1);
            sum += add(map, l, cur);
            if (r == -1) {
                ++cur;
                continue;
            }
            sum += add(map, cur, r);
            cur = r;
        }
        System.out.println(sum);
    }

    private static int add(int[] map, int s, int e) {
        if (s == -1 || e == -1) {
            return 0;
        }
        int sum = 0;
        int h = Math.min(map[s], map[e]);
        for (int i = s + 1; i < e; i++) {
            int diff = h - map[i];
            sum += diff;
            map[i] += diff;
        }
        return sum;
    }

    private static int find(int[] map, int cur, int d) {
        int h = map[cur];
        cur += d;
        while (cur >= 0 && cur < W) {
            if (map[cur] >= h) {
                return cur;
            }
            cur += d;
        }
        return -1;
    }
}
