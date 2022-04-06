import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사_어정윤 {

    private static final int TIME = 0;
    private static final int PAY = 1;

    private static int n;
    private static int maxProfit;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        int[][] schedules = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int time = Integer.parseInt(stringTokenizer.nextToken());
            int pay = Integer.parseInt(stringTokenizer.nextToken());
            schedules[i] = new int[]{time, pay};
        }

        for (int i = 1; i <= n; i++) {
            combine(schedules, i, schedules[i][PAY]);
        }
        System.out.println(maxProfit);
    }

    private static void combine(int[][] schedules, int start, int totalProfit) {
        if (start + schedules[start][TIME] - 1 == n) {
            maxProfit = Math.max(maxProfit, totalProfit);
            return;
        }

        if (start >= n) {
            return;
        }

        for (int i = start+schedules[start][TIME]; i <= n; i++) {
            combine(schedules, i, totalProfit+schedules[i][PAY]);
        }
    }
}
