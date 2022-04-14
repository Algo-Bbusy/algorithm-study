import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;
        int cityNumber = Integer.parseInt(bufferedReader.readLine());
        int busNumber = Integer.parseInt(bufferedReader.readLine());
        int[][] busInfos = new int[cityNumber + 1][cityNumber + 1];
        int maxValue = 100000 * cityNumber + 1;
        for (int[] busInfo : busInfos) {
            Arrays.fill(busInfo, maxValue);
        }

        for (int i = 0; i < busNumber; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int cost = Integer.parseInt(stringTokenizer.nextToken());
            busInfos[from][to] = Math.min(busInfos[from][to], cost);
        }

        for (int noVisit = 1; noVisit <= cityNumber; noVisit++) {
            busInfos[noVisit][noVisit] = 0;
        }

        for (int middle = 1; middle <= cityNumber; middle++) {
            for (int from = 1; from <= cityNumber; from++) {
                if (middle == from) {
                    continue;
                }
                for (int to = 1; to <= cityNumber; to++) {
                    if (from == to || middle == to) {
                        continue;
                    }
                    if (busInfos[from][to] > busInfos[from][middle] + busInfos[middle][to]) {
                        busInfos[from][to] = busInfos[from][middle] + busInfos[middle][to];
                    }
                }
            }
        }

        for (int from = 1; from <= cityNumber; from++) {
            for (int to = 1; to <= cityNumber; to++) {
                stringBuilder.append(busInfos[from][to] == maxValue ? 0 : busInfos[from][to])
                        .append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
