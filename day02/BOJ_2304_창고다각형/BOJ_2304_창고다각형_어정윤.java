import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ_2304_창고다각형_어정윤 {

    private static final int X = 0;
    private static final int Y = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int n = Integer.parseInt(bufferedReader.readLine());
        List<int[]> bars = new LinkedList<>();
        int maxY = 0;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            if (maxY < y) {
                maxY = y;
            }
            bars.add(new int[]{x, y});
        }

        List<int[]> sortedBars = bars.stream()
                .sorted(Comparator.comparing(bar -> bar[X]))
                .collect(Collectors.toList());
        int maxBarIdx = 0;
        for (int i = 0; i < n; i++) {
            if (sortedBars.get(i)[Y] == maxY) {
                maxBarIdx = i;
                break;
            }
        }

        int result = sortedBars.get(maxBarIdx)[Y];
        int preIdx = 0;
        for (int i = 1; i <= maxBarIdx; i++) {
            int[] bar = sortedBars.get(i);
            int[] previousBar = sortedBars.get(preIdx);
            if (previousBar[Y] <= bar[Y]) {
                int dx = bar[X] - previousBar[X];
                result += dx * previousBar[Y];
                preIdx = i;
            }
        }

        preIdx = n-1;
        for (int i = n-2; i >= maxBarIdx; i--) {
            int[] bar = sortedBars.get(i);
            int[] previousBar = sortedBars.get(preIdx);
            if (previousBar[Y] <= bar[Y]) {
                int dx = previousBar[X] - bar[X];
                result += dx * previousBar[Y];
                preIdx = i;
            }
        }
        System.out.println(result);
    }
}
