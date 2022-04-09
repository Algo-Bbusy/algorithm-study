import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17404_RGB거리2_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] houses = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            houses[i][0] = Integer.parseInt(stringTokenizer.nextToken());
            houses[i][1] = Integer.parseInt(stringTokenizer.nextToken());
            houses[i][2] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int[][] costs = new int[n + 1][3];
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            costs[1][i] = houses[1][i];
            costs[1][(i + 1) % 3] = 1001;
            costs[1][(i + 2) % 3] = 1001;
            for (int j = 2; j <= n; j++) {
                costs[j][0] = Math.min(costs[j - 1][1], costs[j - 1][2]) + houses[j][0];
                costs[j][1] = Math.min(costs[j - 1][0], costs[j - 1][2]) + houses[j][1];
                costs[j][2] = Math.min(costs[j - 1][0], costs[j - 1][1]) + houses[j][2];
            }
            minCost = Math.min(minCost, Math.min(costs[n][(i + 1) % 3], costs[n][(i + 2) % 3]));
        }
        System.out.println(Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]));
    }
}
