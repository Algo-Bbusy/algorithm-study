import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1592_영식이와친구들_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());
        int l = Integer.parseInt(stringTokenizer.nextToken());

        int[] friends = new int[n];
        int cnt = 0;
        int idx = 0;
        while (true) {
            friends[idx]++;
            if (isContainM(friends, m)) {
                break;
            }
            if (friends[idx] % 2 == 0) {
                idx = (idx + l) % n;
            } else {
                idx -= l;
                if (idx < 0) {
                    idx += n;
                }
            }
            cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean isContainM(int[] friends, int m) {
        for (int friend : friends) {
            if (friend == m) {
                return true;
            }
        }
        return false;
    }
}
